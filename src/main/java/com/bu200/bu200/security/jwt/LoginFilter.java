package com.bu200.bu200.security.jwt;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


//요청을 중간에서 가로채는 필터다.
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * UsernamePasswordAuthenticationToken 이라는 바구니에 담아서 AuthenticationManager 에게 던져준다.
     * @param request 유저의 정보를 받는 인자
     * @param response ??
     * @return ??
     * @throws AuthenticationException 이 메서드 안에서 에러나면 인증에러를 던짐
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println(request);
        String accountId = obtainUsername(request); //id를 빼옴
        String accountPassword = obtainPassword(request);   //pw를 빼옴

        System.out.println(accountId);
        System.out.println(accountPassword);

        //securiry에서 ID PW를 검증하기 위해선 token에 담아야함.
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(accountId,accountPassword,null);

        //token에 담은 검증을 위한 매니저로 전달
        return authenticationManager.authenticate(authToken);
    }

    /**
     * 로그인에 성공하면 이 메서드가 실행된다.
     * @param request 요청
     * @param response 응답
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("로그인 성공");

    }

    /**
     * 로그인에 실패하면 이 메서드가 실행된다.
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("로그인 실패");

    }
}
