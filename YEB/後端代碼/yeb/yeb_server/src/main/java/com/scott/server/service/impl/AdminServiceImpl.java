package com.scott.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scott.server.config.configSecurity.JwtTokenUtils;
import com.scott.server.pojo.Admin;
import com.scott.server.mapper.AdminMapper;
import com.scott.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scott.server.utils.RespBean;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author scott
 * @since 2023-11-30
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private UserDetailsService userDetailsService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public RespBean login(String userName, String password, HttpServletRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//        if (userDetails == null|| !passwordEncoder.matches(password,userDetails.getPassword())){
//            return RespBean.error("密碼錯誤");
//        }
        if(!userDetails.isEnabled()){
            return RespBean.error("帳號被禁用");
        }
        //更新security登入對象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,null,userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登入成功",tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String userName) {
        // MybatisPlus可以這樣搜尋一個用戶
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username",userName)
                .eq("enable",true)//帳戶是否被禁用
        );
    }
}
