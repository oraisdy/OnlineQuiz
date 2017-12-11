package com.example.qs.service;

import com.example.qs.entity.Exam;
import com.example.qs.entity.Quiz;
import com.example.qs.entity.User;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/7.
 */
public interface ExamService {

    String generateQuiz(Quiz quiz);

    void sendPasswords(Exam exam, List<User> candidates);

    void saveAnswers();


}
