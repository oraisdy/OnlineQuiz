package com.example.qs;

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

    @Test
    public void testAddStudents() {
        User u = new User();
        u.setName("a");
        u.setEmail("123@163.com");
        u.setClassnum("201401");
        List<User> users = new ArrayList<>();
        users.add(u);
        service.saveStudents(users);

        System.out.println(service.showStudentsByClass("201401").size());
    }
}
