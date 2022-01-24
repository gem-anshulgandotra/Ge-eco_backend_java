package com.geco.admin.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.geco.admin.helper.JwtHelper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    public JwtHelper jwtHelper=new JwtHelper();




   
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String reqToken=request.getHeader("Authorization");
                String jwtToken="";               
                if(reqToken!=null && reqToken.startsWith("Bearer ") ){
jwtToken=reqToken.substring(7);
String username = jwtHelper.getUserNameFromJwtToken(jwtToken);
GrantedAuthority authority=new SimpleGrantedAuthority(username);
UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(username,jwtToken, Arrays.asList(authority));
SecurityContextHolder.getContext().setAuthentication(auth);


filterChain.doFilter(request, response);
                }
else{
                filterChain.doFilter(request, response);
}
    }
    
}
