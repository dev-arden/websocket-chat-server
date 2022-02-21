package com.websocket.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.websocket.chat.service.AccountService;


/*
 * 
 * .authorizeRequests() // 6
            .antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
            .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
            .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
            .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
        .and() 
          .formLogin() // 7
            .loginPage("/login") // 로그인 페이지 링크
            .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
        .and()
          .logout() // 8
            .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
	    .invalidateHttpSession(true) // 세션 날리기
    ;
 * 
 * 
 * */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable() // 기본값이 on인 csrf 취약점 보안을 해제한다. on으로 설정해도 되나 설정할경우 웹페이지에서 추가처리가 필요함.
        .headers()
            .frameOptions().sameOrigin() // SockJS는 기본적으로 HTML iframe 요소를 통한 전송을 허용하지 않도록 설정되는데 해당 내용을 해제한다.
        .and()
            .formLogin() // 권한없이 페이지 접근하면 로그인 페이지로 이동한다.
            //.loginPage("/login") // 로그인 페이지 링크
            .defaultSuccessUrl("/chat/room") // 로그인 성공 후 리다이렉트 주소
        .and()
	        .logout() // 8
	        .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
	    .and()
            .authorizeRequests()
	            .antMatchers("/login", "/signup", "/members").permitAll() // 누구나 접근 허용
	            .antMatchers("/chat/**").hasRole("USER") // USER, ADMIN만 접근 가능
	            .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
	            .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
	         ;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	
}