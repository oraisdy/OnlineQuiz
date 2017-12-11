package com.example.qs.dao;

import com.example.qs.entity.ExamUser;
import com.example.qs.entity.ExamUserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by phoebegl on 2017/12/11.
 */
@Repository
public interface ExamUserDao extends JpaRepository<ExamUser, ExamUserPK> {


}
