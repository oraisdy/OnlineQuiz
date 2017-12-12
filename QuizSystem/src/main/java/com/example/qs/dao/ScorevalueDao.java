package com.example.qs.dao;

import com.example.qs.entity.ScoreValue;
import com.example.qs.entity.ScoreValuePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/11.
 */
@Repository
public interface ScorevalueDao extends JpaRepository<ScoreValue, ScoreValuePK> {

    List<ScoreValue> findByExamid(int examid);

    List<ScoreValue> findByExamidAndProblemnum(int examid,int problemnum);
}
