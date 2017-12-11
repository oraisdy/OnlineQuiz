package com.example.jw.dao;

import com.example.jw.domain.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ExamMapper")
public interface ExamMapper {
    @Select("select * from exam where Id = #{Id}")
    Exam getByExamId(@Param("Id") int Id);
}
