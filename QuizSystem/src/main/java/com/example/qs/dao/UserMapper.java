package com.example.qs.dao;

import com.example.qs.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "UserMapper")
public interface UserMapper {
    @Select("select * from user where Id = #{Id}")
    User getById(@Param("Id") String Id);
}
