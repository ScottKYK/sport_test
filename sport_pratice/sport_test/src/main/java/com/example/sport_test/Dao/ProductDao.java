package com.example.sport_test.Dao;

import com.example.sport_test.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    List<Product> getProduct();
    Product getDetail(String title);
}
