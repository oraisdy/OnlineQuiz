package com.example.jw.controller;

import com.example.jw.dao.*;
import com.example.jw.domain.*;
import com.example.jw.model.Question;
import com.example.jw.service.QCService;
import com.example.jw.service.ScoreService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by dora on 2017/11/22.
 */
@RestController
public class DcController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ScoreService scoreService;

    @Autowired
    QCService qcService;

//    @Value("${application.qc.name}")
//    private String qcName;

    @CrossOrigin
    @RequestMapping(value = "/getScore", method = RequestMethod.GET)
    public List getScore(HttpServletRequest request) {
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        if(request.getParameter("userid")==null){
            return scoreService.getScoreByExamId(quizid);
        }
        int userid = Integer.parseInt(request.getParameter("userid"));

        return scoreService.getScore(userid, quizid);
    }

    @CrossOrigin
    @RequestMapping(value = "/calScore", method = RequestMethod.GET)
    public List calScore(HttpServletRequest request) {
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        if(request.getParameter("userid")==null){
            return scoreService.calScoreByExamId(quizid);
        }
        int userid = Integer.parseInt(request.getParameter("userid"));

        return scoreService.calScore(userid, quizid);
    }

    @CrossOrigin
    @RequestMapping(value = "/updateScore", method = RequestMethod.GET)
    public int updateScore(HttpServletRequest request) {
        int score = Integer.parseInt(request.getParameter("score"));
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        if(request.getParameter("userid")==null){
            return scoreService.updateScoreByExamId(score, quizid);
        }
        int userid = Integer.parseInt(request.getParameter("userid"));
        return scoreService.updateScore(score, userid, quizid);
    }

    @CrossOrigin
    @RequestMapping(value = "/saveTheInputExcel" ,method = RequestMethod.POST)
    public Vector<Question> saveTheInputExcel(@RequestBody MultipartFile file) throws IOException, InvalidFormatException {
        return qcService.saveTheInputExcel(file);
    }

    @CrossOrigin
    @RequestMapping(value = "/getQuestionsByIDs" ,method = RequestMethod.POST)
    public Vector<Question> getQuestionsByIDs(@RequestBody ArrayList<Integer> ids){
        return qcService.getQuestionsByIDs(ids);
    }

    @CrossOrigin
    @RequestMapping(value = "/getQuestions" ,method = RequestMethod.GET)
    public Vector<Question> getQuestions(HttpServletRequest request){
        return qcService.getQuestions(request);
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllSubjects" ,method = RequestMethod.GET)
    public Vector<String> getAllSubjects(){
        return qcService.getAllSubjects();
    }

    @CrossOrigin
    @RequestMapping(value = "/getTags" ,method = RequestMethod.GET)
    public Vector<String> getTags(HttpServletRequest request){
        return qcService.getTags(request);
    }



//    @CrossOrigin
//    @GetMapping("/consumer")
//    public String testAdd() {
//        ServiceInstance serviceInstance = loadBalancerClient.choose(qcName);
//        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/add?a=2&b=3";
//        System.out.println(url);
//        return restTemplate.getForObject(url, String.class);
//    }


}
