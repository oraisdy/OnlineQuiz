package com.example.qs;

import com.example.qs.dao.ChoiceDao;
import com.example.qs.entity.Choice;
import com.example.qs.entity.User;
import com.example.qs.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/12/10.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private ChoiceDao choiceDao;

    @Test
    public void testAddStudents() {
//        User u = new User();
//        u.setName("a");
//        u.setEmail("123@163.com");
//        u.setClassnum("201401");
//        List<User> users = new ArrayList<>();
//        users.add(u);
//        service.saveStudents(users);
//
//        System.out.println(service.showStudentsByClass("201401").size());
    }

    @Test
    public void testGetAnswers() {
        List<Integer> ans = choiceDao.getStudentAns(14,141250051,11);
//        System.out.println(ans.size());

        List<Integer> exist = choiceDao.getQuestionsByExamidAndUserid(12,141250051);
        for(Integer i : exist)
            System.out.println(i);
    }
}
