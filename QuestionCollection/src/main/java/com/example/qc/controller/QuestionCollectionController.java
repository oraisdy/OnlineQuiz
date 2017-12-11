package com.example.qc.controller;

import com.example.qc.model.Answer;
import com.example.qc.model.Question;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Vector;

@RestController
public class QuestionCollectionController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String applicationName;


    private String url="jdbc:mysql://118.89.114.168:3306/online_quiz?autoReconnect=true&useSSL=false&characterEncoding=utf-8";
    private String user="root";
    private String password="2a617";
    private Connection conn;

//    public static void main(String[] args) throws Exception{
//        QuestionCollectionController qc = new QuestionCollectionController();
//        File file = new File("/Users/dym/Documents/大作业/输入的试题.xlsx");
//        FileInputStream in_file = new FileInputStream(file);
//
//        // 转 MultipartFile
//        MultipartFile multi = new MockMultipartFile("模板. xls", in_file);
//        System.out.println(qc.saveTheInputExcel(multi).size());
//    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @CrossOrigin
    @RequestMapping(value = "/saveTheInputExcel" ,method = RequestMethod.POST)
    public Vector<Question> saveTheInputExcel(@RequestBody MultipartFile file) throws EncryptedDocumentException, InvalidFormatException, IOException {
        Vector<Question> result = new Vector<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        try{
            conn.setAutoCommit(false);
            String sql ="INSERT INTO questions VALUES(NULL,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (Row row : sheet) {
                String question = formatter.formatCellValue(row.getCell(0));
                String subject = formatter.formatCellValue(row.getCell(1));
                String[] trueAnswers = formatter.formatCellValue(row.getCell(2)).split(" ");
                String tag = formatter.formatCellValue(row.getCell(4));

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
            Vector<Integer> questionIDs = new Vector<>();
            while (rs.next()){
                int questionID = rs.getInt(1);
                questionIDs.add(questionID);
                Row row = sheet.getRow(index);
                String[] trueAnswers = formatter.formatCellValue(row.getCell(2)).split(" ");
                String[] falseAnswers = formatter.formatCellValue(row.getCell(3)).split(" ");

                for (String answer : trueAnswers){
                    pstmt.setInt(1,questionID);
                    pstmt.setString(2,answer);
                    pstmt.setInt(3,1);
                    pstmt.addBatch();
                }

                for (String answer : falseAnswers){
                    pstmt.setInt(1,questionID);
                    pstmt.setString(2,answer);
                    pstmt.setInt(3,0);
                    pstmt.addBatch();
                }
            }
            pstmt.executeBatch();
            conn.commit();
            pstmt.close();
            conn.close();
            for (int i = 0 ;i < questionIDs.size();i++){
                result.add(getQuestionByID(questionIDs.get(i)));
            }
            return result;
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                return null;
            }

            return null;
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/getQuestionsByTagAndSubject" ,method = RequestMethod.GET)
    public Vector<Question> getQuestionsByTagAndSubject(@RequestParam String tag, @RequestParam String subject){

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
        return result;
    }

    private Question getQuestionByID(int id){
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        try{
            String sql="select * from questions where ID=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,id);
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
                return question;
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
            return null;
        }
        return null;
    }
}
