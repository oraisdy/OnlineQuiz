package com.example.qs.service;

/**
 * Created by phoebegl on 2017/12/6.
 */
public interface EmailService {

    int sendMessage(String subject, String Message, String candidates);
}
