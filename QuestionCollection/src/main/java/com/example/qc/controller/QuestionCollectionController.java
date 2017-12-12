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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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
    @RequestMapping(value = "/getQuestionsByIDs" ,method = RequestMethod.POST)
    public Vector<Question> getQuestionsByIDs(@RequestBody ArrayList<Integer> ids){
        Vector<Question> result = new Vector<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(getQuestionByID(ids.get(i)));
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/getQuestions" ,method = RequestMethod.GET)
    public Vector<Question> getQuestions(HttpServletRequest request){
        if ((request.getParameter("tag") != null) && (request.getParameter("subject") != null)){
            return getQuestionsByTagAndSubject(request.getParameter("tag"),request.getParameter("subject"));
        }
        else if (request.getParameter("subject") != null){
            return getQuestionsByParameter("Subject",request.getParameter("subject"));
        }
        else if (request.getParameter("tag") != null){
            return getQuestionsByParameter("Tag",request.getParameter("tag"));
        }
        else{
            return getAllQuestions();
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllSubjects" ,method = RequestMethod.GET)
    public Vector<String> getAllSubjects(){
        Vector<String> result = new Vector<>();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        try{
            String sql="select DISTINCT Subject from questions";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                result.add(rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
            return null;
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "/getTags" ,method = RequestMethod.GET)
    public Vector<String> getTags(HttpServletRequest request){
        Vector<String> result = new Vector<>();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        try{
            String sql="select DISTINCT Tag from questions";
            if (request.getParameter("subject") != null){
                sql = "SELECT DISTINCT Tag FROM questions WHERE Subject=\""+request.getParameter("subject")+"\"";
            }
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                result.add(rs.getString(1));
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
            return null;
        }
        return result;
    }

    private Vector<Question> getAllQuestions(){
        Vector<Question> result = new Vector<>();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        try{
            String sql="select * from questions";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                int questionId = rs.getInt("ID");
                Question question = new Question(questionId,
                        rs.getString("Content"),
                        getAnswersByQuestionID(questionId),
                        rs.getInt("AnswerNumber"),
                        rs.getString("Subject"),
                        rs.getString("Tag"));
                result.add(question);
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
            return null;
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
                Question question = new Question(questionId,
                        rs.getString("Content"),
                        getAnswersByQuestionID(questionId),
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

    private Vector<Answer> getAnswersByQuestionID(int questionId){
        Vector<Answer> allAnswers = new Vector<>();
        String sql="select * from answers where QuestionID=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,questionId);
            ResultSet answers = pstmt.executeQuery();
            while (answers.next()){
                Answer answer = new Answer(answers.getInt("ID"),
                        answers.getString("Content"),
                        answers.getInt("QuestionID"),
                        answers.getInt("Type"));
                allAnswers.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAnswers;

    }

    private Vector<Question> getQuestionsByTagAndSubject(String tag, String subject){

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
                Question question = new Question(questionId,
                        rs.getString("Content"),
                        getAnswersByQuestionID(questionId),
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

    private Vector<Question> getQuestionsByParameter(String name,String value){
        Vector<Question> result = new Vector<>();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        try{
            String sql="select * from questions where "+name+"=\""+value+"\"";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
                int questionId = rs.getInt("ID");
                Question question = new Question(questionId,
                        rs.getString("Content"),
                        getAnswersByQuestionID(questionId),
                        rs.getInt("AnswerNumber"),
                        rs.getString("Subject"),
                        rs.getString("Tag"));
                result.add(question);
            }
        }catch (SQLException e){
            e.printStackTrace();
            e.getNextException();
            return null;
        }
        return result;
    }
}
