package com.bu200.bu200.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  //스프링 설정 파일이다.
@EnableWebSecurity
public class SecurityConfig {
    @Bean   //빈이기 때문에 주입 가능하다.
    public BCryptPasswordEncoder bCryptPasswordEncoder(){   //패스워드 인코드
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf 를 disable 하기
        http
                .csrf(AbstractHttpConfigurer::disable);
        //form 로그인 방식 disable
        http
                .formLogin(AbstractHttpConfigurer::disable);
        //http basic 방식 disable
        http
                .httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/login", "/", "/join").permitAll()   //"login", "/", "/join" 경로는 모든 권한을 허용함
                        .requestMatchers("/admin").hasRole("ADMIN")             // "/admin" 경로는 ADMIN 권한이 있는지 확인함
                        .anyRequest().authenticated());     //나머지 경로는 모두 권한을 확인함

        http
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));   //세션을 stateless 상태로 설정함

        return http.build();
    }
}
