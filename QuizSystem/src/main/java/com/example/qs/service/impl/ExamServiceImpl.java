package com.example.qs.service.impl;

import com.example.qs.Util.DateUtil;
import com.example.qs.Util.Encrypt;
import com.example.qs.dao.ExamDao;
import com.example.qs.entity.*;
import com.example.qs.service.ExamService;
import com.example.qs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamDao examDao;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Value("${application.es.name}")
    private String esName;

    @Override
    public String generateQuiz(Quiz quiz) {

        Exam exam = new Exam();
        exam.setName(quiz.getName());
        exam.setStartat(DateUtil.transferDate(quiz.getStart_time()));
        exam.setEndat(DateUtil.calEndTime(quiz.getStart_time(), quiz.getLast_time()));
        exam.setQuestionCount(quiz.getProblem_count());
        exam.setSubject(quiz.getSubject());

        exam = examDao.save(exam);

        /*
        保存用户
         */
        List<User> candidates = new ArrayList<>();
        for(String s : quiz.getClasses()) {
            List<User> students = userService.showStudentsByClass(s);
            candidates.addAll(students);
        }
        userService.corelationExamUser(exam.getId(), candidates);

        /*
        发送密码
         */
        sendPasswords(exam, candidates);

        //TODO add to scorevalue
        return  "";
    }

    @Override
    public void sendPasswords(Exam exam, List<User> candidates) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(esName);

        String url = "http://localhost:5555/sendEmail";
        for (User u : candidates) {
            String subject = exam.getName()+"考试密码";
            String content = Encrypt.aes(String.valueOf(exam.getId())+String.valueOf(u.getId()));
            Email email = new Email();
            email.setSubject(subject);
            email.setContent(content);
            email.setReceiver(u.getEmail());
            restTemplate.postForEntity(url, email, String.class);
        }
    }

    @Override
    public void saveAnswers() {

    }
}
