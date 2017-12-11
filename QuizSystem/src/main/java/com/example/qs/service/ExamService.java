package com.example.qs.service;

import com.example.qs.entity.Exam;
import com.example.qs.entity.Quiz;
import com.example.qs.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by phoebegl on 2017/12/7.
 */
public interface ExamService {

    String generateQuiz(Quiz quiz);

    void sendPasswords(Exam exam, List<User> candidates);

    Map<String, Object> generatePaper(String authcode);

    int saveAnswer(Map<String, Object> choices);
}
