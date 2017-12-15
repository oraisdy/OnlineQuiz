package com.example.qs.dao;

import com.example.qs.entity.Choice;
import com.example.qs.entity.ChoicePK;
import com.example.qs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by phoebegl on 2017/12/10.
 */
@Repository
public interface ChoiceDao extends JpaRepository<Choice, ChoicePK>{

    List<Choice> findByExamidAndUserid(int examid, int userid);

    @Query("select answerid from Choice where examid=?1 and userid=?2 and questionid=?3 and answerid!=-1")
    List<Integer> getStudentAns(int examid, int userid, int questionid);

    @Query("select distinct questionid from Choice where examid=?1 and userid=?2 order by problemnum asc")
    List<Integer> getQuestionsByExamidAndUserid(int examid, int userid);
}
