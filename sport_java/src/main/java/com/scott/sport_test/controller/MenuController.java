package com.scott.sport_test.controller;

import com.alibaba.fastjson.JSON;
import com.scott.sport_test.dao.MenuDao;
import com.scott.sport_test.pojo.MainMenu;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@MapperScan("com.scott.sport_test.dao")
@Controller
@ResponseBody
public class MenuController {
    @Autowired
    MenuDao menuDao;

    @RequestMapping("/menus")
    public String getAllMenus() {
        List<MainMenu> menus = menuDao.getMenus();
        HashMap<String, Object> map = new HashMap<>();
        if (menus == null) {
            map.put("flag", 404);
        }else{
            map.put("flag",200);
            map.put("menus",menus);
        }
        String s = JSON.toJSONString(map);
        System.out.println(s);
        return s;
    }
}
