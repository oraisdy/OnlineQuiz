package com.example.jw.controller;

import com.example.jw.dao.*;
import com.example.jw.domain.*;
import com.example.jw.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Value("${application.qc.name}")
    private String qcName;

    @RequestMapping(value = "/getScore", method = RequestMethod.GET)
    public List getScore(HttpServletRequest request) {
        int quizid = Integer.parseInt(request.getParameter("quizid"));
        if(request.getParameter("userid")==null){
            return scoreService.getScoreByExamId(quizid);
        }
        int userid = Integer.parseInt(request.getParameter("userid"));

        return scoreService.getScore(userid, quizid);
    }

    @GetMapping("/consumer")
    public String testAdd() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(qcName);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/add?a=2&b=3";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
