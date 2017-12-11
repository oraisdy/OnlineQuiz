package com.example.jw.service;

import com.example.jw.dao.Exam_userMapper;
import com.example.jw.domain.Exam_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    Exam_userMapper Exam_userMapper;

    public List getScore(int user_id, int exam_id) {
        ArrayList<Exam_user> list = new ArrayList<Exam_user>();
        Exam_user exam_user = Exam_userMapper.getUniqueScore(141250050, 5);
        list.add(exam_user);
        return list;
    }

    public List getScoreByExamId(int quizid){
        return Exam_userMapper.getScoreByExamId(5);
    }

    public int calculateScore(){
        return 123;
    }

    public int getAnswer() {return 123;}
}
