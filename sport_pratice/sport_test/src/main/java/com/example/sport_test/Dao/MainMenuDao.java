package com.example.sport_test.Dao;

import com.example.sport_test.pojo.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainMenuDao {
    List<MainMenu> getMainMenu();
}
