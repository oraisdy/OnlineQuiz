package com.example.jw.dao;

import com.example.jw.domain.Statistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "StatisticMapper")
public interface StatisticMapper {
    @Select("select choice.examid as exam_id, " +
                    "choice.userid as user_id, " +
                    "sum(scorevalue.Scorevalue) as total " +
            "from choice " +
            "join answers on choice.questionid = answers.QuestionID and choice.answerid = answers.ID " +
            "join scorevalue on choice.examid = scorevalue.exam_id and choice.questionid = scorevalue.question_id and choice.problem_num = scorevalue.Problem_num " +
            "where choice.examid = #{exam_id} and choice.userid = #{user_id} and answers.Type = 1 group by exam_id, user_id")
    Statistic getUniqueStat(@Param("exam_id") int exam_id, @Param("user_id") int user_id);

    @Select("select choice.examid as exam_id, " +
            "choice.userid as user_id, " +
            "sum(scorevalue.Scorevalue) as total " +
            "from choice " +
            "join answers on choice.questionid = answers.QuestionID and choice.answerid = answers.ID " +
            "join scorevalue on choice.examid = scorevalue.exam_id and choice.questionid = scorevalue.question_id and choice.problem_num = scorevalue.Problem_num " +
            "where choice.examid = #{exam_id} and answers.Type = 1 " +
            "group by choice.examid,choice.userid")
    List<Statistic> getStatByExamId(@Param("exam_id") int exam_id);
}
