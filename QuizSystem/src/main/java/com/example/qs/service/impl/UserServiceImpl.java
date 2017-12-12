package com.example.qs.service.impl;

import com.example.qs.dao.ExamUserDao;
import com.example.qs.dao.UserDao;
import com.example.qs.entity.ExamUser;
import com.example.qs.entity.ExamUserPK;
import com.example.qs.entity.User;
import com.example.qs.service.UserService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/12/9.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ExamUserDao examUserDao;

    @Override
    public List<User> showStudentsByClass(String classid) {
        return userDao.findByClassnum(classid);
    }

    @Override
    public List<User> saveStudents(MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();

            List<User> result = new ArrayList<>();
            for (Row row : sheet) {
                User user = new User();
                user.setName(formatter.formatCellValue(row.getCell(0)));
                user.setEmail(formatter.formatCellValue(row.getCell(1)));
                user.setClassnum(formatter.formatCellValue(row.getCell(2)));
                userDao.save(user);
                result.add(user);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidFormatException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void corelationExamUser(int examid, List<User> candidates) {
        for (User u : candidates) {
            ExamUserPK pk = new ExamUserPK();
            pk.setUserid(u.getId());
            pk.setExamid(examid);
            ExamUser examUser = new ExamUser();
            examUser.setId(pk);
            examUserDao.save(examUser);
        }
    }
}
