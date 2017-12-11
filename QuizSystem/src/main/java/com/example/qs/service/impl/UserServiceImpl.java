package com.example.qs.service.impl;

import com.example.qs.dao.ExamUserDao;
import com.example.qs.dao.UserDao;
import com.example.qs.entity.ExamUser;
import com.example.qs.entity.ExamUserPK;
import com.example.qs.entity.User;
import com.example.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int saveStudents(List<User> users) {
        for(User user : users) {
            user.setRole("Student");
            userDao.save(user);
        }
        return 0;
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
