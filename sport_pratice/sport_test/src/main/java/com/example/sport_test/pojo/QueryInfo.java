package com.example.sport_test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryInfo {
    private String username;
    private int PageNum = 1;
    private int PageSize = 5;

}
