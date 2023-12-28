package com.example.sport_test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubMenu {
    private int id;
    private String title;
    private String path;
    private int mId;

    public SubMenu(String title, String path, int mId) {
        this.title = title;
        this.path = path;
        this.mId = mId;
    }
}
