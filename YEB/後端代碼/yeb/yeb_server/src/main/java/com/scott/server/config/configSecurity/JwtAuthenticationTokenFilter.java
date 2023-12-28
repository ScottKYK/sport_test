//package com.scott.server.config.configSecurity;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Configuration
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    @Value("${jwt.tokenHeader}")
//    private String tokenHeader;
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//    @Autowired
//    private JwtTokenUtils jwtTokenUtils;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        //前端傳給後端的授權
//        String authHeader = request.getHeader(tokenHeader);
//        if(authHeader!= null && authHeader.startsWith(tokenHead)){
//            String authToken = authHeader.substring(tokenHead.length());
//            String userName = jwtTokenUtils.getUserNameFromToken(authToken);
//            //token用戶名存在但未登入
//            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//                //登入
//                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//                //驗證token是否有效
//                if(jwtTokenUtils.validateToken(tokenHead,userDetails)){
//                    UsernamePasswordAuthenticationToken authenticationToken =
//                            new UsernamePasswordAuthenticationToken(userName,null,userDetails.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//        }
//        filterChain.doFilter(request,response);
//
//    }
//}
