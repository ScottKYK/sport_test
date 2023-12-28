//package com.scott.server.config.configSecurity;
//
//import com.scott.server.pojo.Admin;
//import com.scott.server.service.IAdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private IAdminService iAdminService;
//    @Autowired
//    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//
//    //配置訪問頁面權限
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/","/hello").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                //自訂義登入頁面
//                .formLogin(form->form.loginPage("/login").permitAll())
//                //自訂義登出頁面
//                .logout().logoutSuccessUrl("/").permitAll();
//        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    @Bean
//    //重寫WebSecurityConfigurerAdapter內的uerDetailsService(根據用戶名獲取用戶的方法)
//    public UserDetailsService userDetailsService() {
//        return username -> {
//            Admin admin = iAdminService.getAdminByUserName(username);
//            if (admin != null) {
//                return (UserDetails) admin;
//            }
//            return null;
//        };
//    }
//
//    @Bean
//    //重寫WebSecurityConfigurerAdapter內的passwordEncoder(自訂義密碼解析)
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
//        return new JwtAuthenticationTokenFilter();
//    }
//}
