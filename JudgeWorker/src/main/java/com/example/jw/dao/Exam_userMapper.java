package com.example.jw.dao;

import com.example.jw.domain.Exam_user;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "Exam_userMapper")
public interface Exam_userMapper {
    @Select("select * from exam_user where user_id = #{user_id} and exam_id = #{exam_id}")
    Exam_user getUniqueScore(@Param("user_id") int user_id, @Param("exam_id") int exam_id);

    @Select("select * from exam_user where exam_id = #{exam_id}")
    List<Exam_user> getScoreByExamId(@Param("exam_id") int exam_id);
}
