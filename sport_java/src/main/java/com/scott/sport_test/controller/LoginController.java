package com.scott.sport_test.controller;

import com.alibaba.fastjson.JSON;
import com.scott.sport_test.dao.UserDao;
import com.scott.sport_test.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@MapperScan("com.scott.sport_test.dao")
@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        String flag = "error";
        User us = userDao.getUserByMessage(user.getUsername(), user.getPassword());
        if(us!=null){
            flag = "ok";
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("flag",flag);
        map.put("user",us);
        String jsonString = JSON.toJSONString(map);
        System.out.println(us);
        return jsonString;
    }
}
