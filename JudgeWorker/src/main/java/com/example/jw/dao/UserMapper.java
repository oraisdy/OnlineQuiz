package com.example.jw.dao;

import com.example.jw.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "UserMapper")
public interface UserMapper {
    @Select("select * from user where Id = #{Id}")
    User getById(@Param("Id") int Id);
}
