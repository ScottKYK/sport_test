package com.example.sport_test.Controller;


import com.alibaba.fastjson.JSON;
import com.example.sport_test.Dao.UserDao;
import com.example.sport_test.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;

@MapperScan("com.example.sport_test.Dao")
@RestController
public class LoginController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        System.out.println("login user = " + user);
        String flag = "ok";
        User u = userDao.getUserByMessage(user.getUsername(), user.getPassword());
        System.out.println("user = " + u);
        if (u == null) {
            flag = "error";
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        map.put("user", u);
        String s = JSON.toJSONString(map);
        return s;
    }

    @RequestMapping("/test")
    public String test() {
        return "test!";
    }
}
