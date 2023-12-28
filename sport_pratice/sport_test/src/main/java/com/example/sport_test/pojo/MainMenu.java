package com.example.sport_test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainMenu {
    private int id;
    private String title;
    private String path;
    private List<SubMenu> sMenu;

    public MainMenu(String title, String path, List<SubMenu> sMenu) {
        this.title = title;
        this.path = path;
        this.sMenu = sMenu;
    }
}
