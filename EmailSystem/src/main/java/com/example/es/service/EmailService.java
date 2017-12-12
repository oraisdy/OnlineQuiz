package com.example.es.service;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/6.
 */
public interface EmailService {

    int sendMessage(String subject, String Message, String candidates);
}
