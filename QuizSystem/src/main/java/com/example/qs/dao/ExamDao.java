package com.example.qs.dao;

import com.example.qs.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by phoebegl on 2017/12/7.
 */
@Repository
public interface ExamDao extends JpaRepository<Exam, Integer>{

    Exam findById(int examid);
}
