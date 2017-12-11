package com.example.qc.controller;

import com.example.qc.model.Answer;
import com.example.qc.model.Question;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class QuestionCollectionController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;


    private String url="jdbc:mysql://118.89.114.168:3306/online_quiz?autoReconnect=true&useSSL=false&characterEncoding=utf-8";
    private String user="root";
    private String password="2a617";
    private Connection conn;

    public static void main(String[] args){
        QuestionCollectionController qc = new QuestionCollectionController();
        System.out.println(qc.getQuestionsByTagAndSubject("tag","software").size());
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping(value = "/saveTheInputExcel" ,method = RequestMethod.POST)
    public void saveTheInputExcel(InputStream inputStream) throws EncryptedDocumentException, InvalidFormatException, IOException {
        ServiceInstance instance = discoveryClient.getInstances(applicationName).get(0);

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            logger.info("/saveTheInputExcel, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", error:" + e);
            return;
        }
        try{
            conn.setAutoCommit(false);
            String sql ="INSERT INTO questions VALUES(NULL,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (Row row : sheet) {
                String question = formatter.formatCellValue(row.getCell(0));
                String subject = formatter.formatCellValue(row.getCell(1));
                String[] trueAnswers = formatter.formatCellValue(row.getCell(2)).split(" ");
                String tag = formatter.formatCellValue(row.getCell(3));

                pstmt.setString(1,question);
                pstmt.setString(2,trueAnswers.length+"");
                pstmt.setString(3,subject);
                pstmt.setString(4,tag);
                pstmt.addBatch();

            }
            pstmt.executeBatch();
            ResultSet rs = pstmt.getGeneratedKeys();
            int index = 0;
            sql ="INSERT INTO answers VALUES(NULL,?,?,?)";
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            while (rs.next()){
                int questionID = rs.getInt(1);
                Row row = sheet.getRow(index);
                String[] trueAnswers = formatter.formatCellValue(row.getCell(2)).split(" ");
                String[] falseAnswers = formatter.formatCellValue(row.getCell(3)).split(" ");

                for (String answer : trueAnswers){
                    pstmt.setInt(1,questionID);
                    pstmt.setString(2,answer);
                    pstmt.setString(3,"TRUE");
                    pstmt.addBatch();
                }

                for (String answer : falseAnswers){
                    pstmt.setInt(1,questionID);
                    pstmt.setString(2,answer);
                    pstmt.setString(3,"FALSE");
                    pstmt.addBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();
            pstmt.close();
            conn.close();
            logger.info("/saveTheInputExcel, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", success");

        }catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                logger.info("/saveTheInputExcel, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", error:" + e1);
                e1.printStackTrace();
            }
            logger.info("/saveTheInputExcel, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", error:" + e);
            e.printStackTrace();
            e.getNextException();
        }

    }

    @RequestMapping(value = "/getQuestionsByTagAndSubject" ,method = RequestMethod.GET)
    public Vector<Question> getQuestionsByTagAndSubject(@RequestParam String tag, @RequestParam String subject){
        ServiceInstance instance = discoveryClient.getInstances(applicationName).get(0);

        Vector<Question> result = new Vector<>();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return result;
        }
        try{
            //建立PreparedStatement对象
            String sql="select * from questions where Subject=? and Tag=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,subject);
            pstmt.setString(2,tag);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                int questionId = rs.getInt("ID");
                sql="select * from answers where QuestionID=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1,questionId);
                ResultSet answers = pstmt.executeQuery();
                Vector<Answer> allAnswers = new Vector<>();
                while (answers.next()){
                    Answer answer = new Answer(answers.getInt("ID"),
                            answers.getString("Content"),
                            answers.getInt("QuestionID"),
                            answers.getInt("Type"));
                    allAnswers.add(answer);
                }
                Question question = new Question(questionId,
                        rs.getString("Content"),
                        allAnswers,
                        rs.getInt("AnswerNumber"),
                        rs.getString("Subject"),
                        rs.getString("Tag"));
                result.add(question);
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
        }
        logger.info("/getQuestionsByTagAndSubject, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + result);
        return result;
    }
}
