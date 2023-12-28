package com.scott.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "AdminLogin對象",description = "")
public class AdminLoginParam {
    @ApiModelProperty(value = "用戶名",required = true)
    private String userName;
    @ApiModelProperty(value = "密碼",required = true)
    private String password;
}
