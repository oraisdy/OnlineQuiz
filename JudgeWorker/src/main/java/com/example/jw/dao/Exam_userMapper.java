package com.example.jw.dao;

import com.example.jw.domain.Exam_user_full;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "Exam_userMapper")
public interface Exam_userMapper {
    @Select("select exam_id, score, user.* from exam_user join user on exam_user.user_id = user.Id where user_id = #{user_id} and exam_id = #{exam_id}")
    Exam_user_full getUniqueScore(@Param("user_id") int user_id, @Param("exam_id") int exam_id);

    @Select("select exam_id, score, user.* from exam_user join user on exam_user.user_id = user.Id where exam_id = #{exam_id}")
    List<Exam_user_full> getScoreByExamId(@Param("exam_id") int exam_id);

    @Update("update exam_user set Score = #{score} where user_id = #{user_id} and exam_id = #{exam_id}")
    int updateUniqueScore(@Param("score") int score, @Param("user_id") int user_id, @Param("exam_id") int exam_id);

    @Update("update exam_user set Score = #{score} where exam_id = #{exam_id}")
    int updateScoreByExamId(@Param("score") int score, @Param("exam_id") int exam_id);
}
