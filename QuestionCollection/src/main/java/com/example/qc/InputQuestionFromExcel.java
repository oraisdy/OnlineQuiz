package  com.example.qc;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


public class InputQuestionFromExcel {

    public static void main(String[] args){
        InputQuestionFromExcel inputQuestionFromExcel = new InputQuestionFromExcel();
        try{
            inputQuestionFromExcel.readTheInputExcel("/Users/dym/Documents/大作业/输入的试题.xlsx");

        }
        catch (Exception exception){
            System.out.println(exception);
        }
    }

    public void readTheInputExcel(String path) throws EncryptedDocumentException, InvalidFormatException, IOException {

        String url="jdbc:mysql://localhost:3306/question?autoReconnect=true&useSSL=false&characterEncoding=utf-8";
        String user="root";
        String password="123456";
        Connection conn;


        InputStream inputStream = new FileInputStream(path);
        //InputStream inp = new FileInputStream("C:/Users/H__D/Desktop/workbook.xls");

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();
        try{
            conn= DriverManager.getConnection(url,user,password);
        }
        catch (SQLException e){
            return;
        }
        try{
            conn.setAutoCommit(false);
            String sql ="INSERT INTO questions VALUES(NULL,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            for (Row row : sheet) {
                String question = formatter.formatCellValue(row.getCell(0));
                String subject = formatter.formatCellValue(row.getCell(1));
                String[] trueAnswers = formatter.formatCellValue(row.getCell(2)).split(" ");

                pstmt.setString(1,question);
                pstmt.setString(3,subject);
                pstmt.setString(2,trueAnswers.length+"");
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

        }catch (SQLException e){
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            e.getNextException();
        }


    }

}