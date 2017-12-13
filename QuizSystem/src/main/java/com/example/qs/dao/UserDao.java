package com.example.qs.dao;

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
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> findByClassnum(String classid);

    @Query("select DISTINCT classnum FROM User")
    List<String> findAllClasses();

    User findById(int userid);
}
