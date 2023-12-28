package com.example.sport_test.Dao;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface SportDao {
    HashMap<String,Object> getPercentage();
}
