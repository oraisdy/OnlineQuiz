package com.example.qs.controller;

import com.example.qs.dao.UserMapper;
import com.example.qs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dora on 2017/11/22.
 */
@RestController
public class DcController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;

    @Value("${application.qc.name}")
    private String qcName;

    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(qcName);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/add?a=2&b=3";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @Autowired
    UserMapper UserMapper;

    @GetMapping("/test")
    public String test() {
        User user = UserMapper.getById("141250047");
        return user.getEmail();
    }
}
