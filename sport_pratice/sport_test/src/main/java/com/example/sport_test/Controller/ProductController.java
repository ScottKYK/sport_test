package com.example.sport_test.Controller;

import com.alibaba.fastjson.JSON;
import com.example.sport_test.Dao.ProductDao;

import com.example.sport_test.pojo.Product;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@MapperScan("com.example.sport_test.Dao")
@RestController
public class ProductController {
    @Autowired
    ProductDao productDao;

    @RequestMapping("getCourse")
    public String getCourse() {
        List<Product> product = productDao.getProduct();
        String s = JSON.toJSONString(product);
        System.out.println(s);
        return s;
    }

    @RequestMapping("getDetail")
    public String getDetail(@RequestBody String title) {
        String substring = title.substring(1, 5);
        String s = JSON.toJSONString(productDao.getDetail(substring));
        System.out.println(s);
        return s;
    }
}
