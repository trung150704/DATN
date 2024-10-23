package com.mt;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
				Account user = accountService.findById(username);
				String password = passwordUtil.getBcryBCryptPasswordEncoder().encode(user.getPassword());
				String role = user.isRole() ? "ADMIN" : "USER";
				return User.withUsername(username)
                        .password(password)
                        .roles(role)
                        .build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found");
			}
		});
	}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Cấu hình các yêu cầu URL với phân quyền phù hợp
        http.authorizeRequests()
            .antMatchers("/order/**").hasAnyRole("USER", "ADMIN")  // Chỉ người dùng có vai trò USER hoặc ADMIN mới truy cập được /order/**
            .antMatchers("/admin/**").hasRole("ADMIN")  // Chỉ người dùng có vai trò ADMIN mới truy cập được /admin/**
            .antMatchers("/rest/authorities").hasRole("ADMIN")  // Chỉ người dùng có vai trò ADMIN mới truy cập được /rest/authorities
            .anyRequest().permitAll();  // Tất cả các yêu cầu khác được phép mà không cần xác thực

        // Cấu hình form đăng nhập
        http.formLogin()
            .loginPage("/security/login/form")
            .loginProcessingUrl("/security/login")
            .defaultSuccessUrl("/security/login/success", false)
            .failureUrl("/security/login/error");

        // Cấu hình remember-me
        http.rememberMe()
            .tokenValiditySeconds(86400);  // Token có hiệu lực trong 1 ngày

        // Cấu hình xử lý lỗi truy cập bị từ chối
        http.exceptionHandling()
            .accessDeniedPage("/security/unauthoried");

        // Cấu hình logout
        http.logout()
            .logoutUrl("/security/logoff")
            .logoutSuccessUrl("/security/logoff/success");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    
}
