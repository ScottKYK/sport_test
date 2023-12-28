package com.example.sport_test.Dao;

import com.example.sport_test.pojo.Course;
import com.example.sport_test.pojo.Product;
import com.example.sport_test.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUserByMessage(@Param("username") String username, @Param("password") String password);

    List<Product> getUserCourseById(@Param("id") int id);

    //獲取用戶列表(含模糊查詢)
    List<User> getUserList(@Param("username") String username, @Param("PageNum") int PageNum, @Param("PageSize") int PageSize);

    //獲取用戶個數
    int getUserCounts(@Param("username") String username);

    //添加用戶
    int addUser(User user);

    //刪除用戶
    int deleteUser(@Param("username") String username);

    //修改用戶
    int updateUser(User user);
    //修改狀態
    int updateState(int id , boolean state);
    //獲取用戶
    User getUser(int id);
}
