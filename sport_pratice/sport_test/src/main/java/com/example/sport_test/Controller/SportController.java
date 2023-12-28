package com.example.sport_test.Controller;

import com.example.sport_test.Dao.SportDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@MapperScan("com.example.sport_test.Dao")
@RestController
public class SportController {
    @Autowired
    SportDao sportDao;

    @RequestMapping("getPercentage")
    public String getPercentage() {
        HashMap<String, Object> percentage = sportDao.getPercentage();
        int total = (int) percentage.get("total");
        int current = (int) percentage.get("current");
        Integer SportPercentage = (current*100)/total;
        System.out.println(SportPercentage);
        return SportPercentage.toString();
    }
}
