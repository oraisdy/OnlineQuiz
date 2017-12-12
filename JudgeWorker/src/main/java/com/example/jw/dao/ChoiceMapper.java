package com.example.jw.dao;

import com.example.jw.domain.Choice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "ChoiceMapper")
public interface ChoiceMapper {
    @Select("select * from choice where examid = #{examid}")
    Choice getByExamId(@Param("examid") int examid);
}
