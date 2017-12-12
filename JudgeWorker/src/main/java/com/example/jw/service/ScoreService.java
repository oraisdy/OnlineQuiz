package com.example.jw.service;

import com.example.jw.dao.Exam_userMapper;
import com.example.jw.dao.StatisticMapper;
import com.example.jw.domain.Exam_user;
import com.example.jw.domain.Statistic;
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
        Exam_user exam_user = Exam_userMapper.getUniqueScore(user_id, exam_id);
        list.add(exam_user);
        return list;
    }

    public List getScoreByExamId(int exam_id) {
        return Exam_userMapper.getScoreByExamId(exam_id);
    }

    public int updateScore(int score, int user_id, int exam_id) {
        return Exam_userMapper.updateUniqueScore(score, user_id, exam_id);
    }

    public int updateScoreByExamId(int score, int exam_id) {
        return Exam_userMapper.updateScoreByExamId(score, exam_id);
    }

    @Autowired
    StatisticMapper StatisticMapper;

    public List calScore(int user_id, int exam_id) {
        ArrayList<Statistic> list = new ArrayList<Statistic>();
        Statistic statistic = StatisticMapper.getUniqueStat(exam_id, user_id);
        list.add(statistic);
        return list;
    }

    public List calScoreByExamId(int exam_id) {
        return StatisticMapper.getStatByExamId(exam_id);
    }
}
