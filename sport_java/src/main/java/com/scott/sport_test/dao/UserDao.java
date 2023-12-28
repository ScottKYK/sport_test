package com.scott.sport_test.dao;

import com.scott.sport_test.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUserByMessage(@Param("username")String username,@Param("password") String password);
}
