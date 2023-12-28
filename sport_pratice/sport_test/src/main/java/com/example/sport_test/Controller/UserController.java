package com.example.sport_test.Controller;

import com.alibaba.fastjson.JSON;
import com.example.sport_test.Dao.UserDao;
import com.example.sport_test.pojo.Course;
import com.example.sport_test.pojo.Product;
import com.example.sport_test.pojo.QueryInfo;
import com.example.sport_test.pojo.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@MapperScan("com.example.sport_test.Dao")
@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    @RequestMapping("/getUser")
    public String getUserList(@RequestBody HashMap<String, Object> infoMap, QueryInfo queryInfo) {
        infoMap = (HashMap<String, Object>) infoMap.get("queryInfo");
        queryInfo.setUsername((String) infoMap.get("username"));
        queryInfo.setPageSize((Integer) infoMap.get("PageSize"));
        queryInfo.setPageNum((Integer) infoMap.get("PageNum"));
        System.out.println(queryInfo);

        int numbers = userDao.getUserCounts(queryInfo.getUsername());
        int PageStart = (queryInfo.getPageNum() - 1) * queryInfo.getPageSize();
        List<User> userList = userDao.getUserList(queryInfo.getUsername(), PageStart, queryInfo.getPageSize());
        HashMap<String, Object> map = new HashMap<>();
        map.put("numbers", numbers);
        map.put("userList", userList);
        String s = JSON.toJSONString(map);
        System.out.println(userList);
        return s;
    }

    @RequestMapping("getUserCourseById")
    public String getUserCourseById(@RequestBody int id){
        List<Product> course = userDao.getUserCourseById(id);
        System.out.println(course);
        return JSON.toJSONString(course);
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user) {
        user.setRole("用戶");
        user.setState(true);
        int i = userDao.addUser(user);
        System.out.println("==================");
        System.out.println("user=" + user);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestBody String username) {
        System.out.println("================================================================");
        System.out.println("接收username=" + username);
        int i = userDao.deleteUser(username);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/getAUser")
    public String getUser(@RequestBody int id) {
        System.out.println("id = " + id);
        User user = userDao.getUser(id);
        String s = JSON.toJSONString(user);
        System.out.println("調用getAUser方法," + s);
        return s;
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user) {
        System.out.println("user = " + user);
        int i = userDao.updateUser(user);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/updateState")
    public String updateState(@RequestBody HashMap<String, Object> infoMap){
        System.out.println(infoMap);
        int id = (int) infoMap.get("id");
        boolean state = (boolean) infoMap.get("state");
        int i = userDao.updateState(id,state);
        return i > 0 ? "success" : "error";
    }
}
