package com.mt;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mt.entity.Account;
import com.mt.service.AccountService;
import com.mt.util.PasswordUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                // Tìm kiếm người dùng theo username
                Account user = accountService.findById(username);
                if (user == null) {
                    throw new UsernameNotFoundException(username + " not found");
                }
                
                // Lấy thông tin mật khẩu và mã hóa
                String password = passwordUtil.getBcryBCryptPasswordEncoder().encode(user.getPassword());
                
                // Lấy vai trò của người dùng dựa trên trường `admin`
                String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};
                
                // Xây dựng đối tượng UserDetails cho Spring Security
                return User.withUsername(username)
                           .password(password)
                           .roles(roles)
                           .build();
            } catch (NoSuchElementException e) {
                throw new UsernameNotFoundException(username + " not found");
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")  // Chỉ người dùng có vai trò ADMIN mới truy cập được /admin/**
                .antMatchers("/user/**").authenticated()  // Người dùng phải đăng nhập để truy cập /user/**
                .anyRequest().permitAll()  // Tất cả các yêu cầu khác được phép mà không cần xác thực
            .and()
            .formLogin()
                .loginPage("/security/login/form")
                .permitAll()
                .defaultSuccessUrl("/security/login/success", true)
                .failureUrl("/security/login/error")
            .and()
            .logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success")
                .permitAll();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    
}
