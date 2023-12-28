package com.scott.server.controller;

import com.scott.server.pojo.Admin;
import com.scott.server.pojo.AdminLoginParam;
import com.scott.server.service.impl.AdminServiceImpl;
import com.scott.server.utils.RespBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    AdminServiceImpl adminService;

    @ApiOperation(value = "登入之後返回token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUserName(),adminLoginParam.getPassword(),request);
    }

    @ApiOperation(value = "退出登入")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("退出成功");
    }

    @ApiOperation(value = "獲取用戶對象")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){//Principal 在Springboot Security代表userName
        if(principal == null){
            return null;
        }
        String userName = principal.getName();
        Admin admin = adminService.getAdminByUserName(userName);
        admin.setPassword(null);//為了安全設為null
        return admin;
    }
}
