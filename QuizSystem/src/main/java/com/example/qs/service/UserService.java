package com.example.qs.service;

import com.example.qs.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/9.
 */
public interface UserService {

    List<User> showStudentsByClass(String classid);

    List<User> saveStudents(MultipartFile file);

    void corelationExamUser(int examid, List<User> users);

}
