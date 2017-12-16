package com.example.jw.service;

import com.example.jw.dao.Exam_userMapper;
import com.example.jw.dao.StatisticMapper;
import com.example.jw.dao.UserMapper;
import com.example.jw.domain.Exam_user_full;
import com.example.jw.domain.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    Exam_userMapper Exam_userMapper;

    @Autowired
    UserMapper UserMapper;

    public List<Exam_user_full> getScore(int user_id, int exam_id) {
        Exam_user_full exam_user_full = Exam_userMapper.getUniqueScore(user_id, exam_id);
        if (exam_user_full.getScore() == null) {
            Statistic statistic = calScore(user_id, exam_id).get(0);
            updateScore(statistic.getTotal(), user_id, exam_id);
            exam_user_full.setScore(statistic.getTotal());
        }
        ArrayList<Exam_user_full> list = new ArrayList<Exam_user_full>();
        list.add(exam_user_full);
        return list;
    }

    public List<Exam_user_full> getScoreByExamId(int exam_id) {
        List<Exam_user_full> list = Exam_userMapper.getScoreByExamId(exam_id);
        for (Iterator<Exam_user_full> i = list.iterator(); i.hasNext(); ) {
            Exam_user_full exam_user_full = i.next();
            if (exam_user_full.getScore() == null) {
                Statistic statistic = calScore(exam_user_full.getUser().getId(), exam_id).get(0);
                updateScore(statistic.getTotal(), exam_user_full.getUser().getId(), exam_id);
            }
        }
        list = Exam_userMapper.getScoreByExamId(exam_id);
        return list;
    }

    public int updateScore(int score, int user_id, int exam_id) {
        return Exam_userMapper.updateUniqueScore(score, user_id, exam_id);
    }

    public int updateScoreByExamId(int score, int exam_id) {
        return Exam_userMapper.updateScoreByExamId(score, exam_id);
    }

    @Autowired
    StatisticMapper StatisticMapper;

    public List<Statistic> calScore(int user_id, int exam_id) {
        ArrayList<Statistic> list = new ArrayList<Statistic>();
        Statistic statistic = StatisticMapper.getUniqueStat(exam_id, user_id);
        list.add(statistic);
        return list;
    }

    public List<Statistic> calScoreByExamId(int exam_id) {
        return StatisticMapper.getStatByExamId(exam_id);
    }
}
