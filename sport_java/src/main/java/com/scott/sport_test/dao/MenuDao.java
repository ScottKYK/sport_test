package com.scott.sport_test.dao;

import com.scott.sport_test.pojo.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    List<MainMenu> getMenus();
}
