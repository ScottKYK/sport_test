package com.example.sport_test.Controller;

import com.alibaba.fastjson.JSON;
import com.example.sport_test.Dao.MainMenuDao;
import com.example.sport_test.pojo.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class MainMenuController {
    @Autowired
    MainMenuDao mainMenuDao;

    @RequestMapping("/mainMenu")
    public String getMainMenu(){
        String flag = "error";
        HashMap<String, Object> map = new HashMap<>();
        List<MainMenu> mainMenu = mainMenuDao.getMainMenu();
        if(mainMenu != null){
            flag = "ok";
            map.put("menu",mainMenu);
        }
        map.put("flag",flag);
        String s = JSON.toJSONString(map);
        return s;
    }
}
