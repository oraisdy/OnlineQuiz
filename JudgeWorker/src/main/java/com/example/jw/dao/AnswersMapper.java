package com.example.jw.dao;

import com.example.jw.domain.Answers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "AnswersMapper")
public interface AnswersMapper {
    @Select("select * from answers where Id = #{Id}")
    Answers getById(@Param("Id") int Id);
}
