package com.example.spring_security.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Component
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("---[LoggingFilter] 초기화됨");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info("---[LoggingFilter] 요청 URI: {}", httpRequest.getRequestURI());

        // 다음 필터 체인 실행 (Spring Security FilterChain 포함)
        chain.doFilter(request, response);

        log.info("---[LoggingFilter] 응답 완료");
    }

    @Override
    public void destroy() {
        log.info("---[LoggingFilter] 소멸됨");
    }
}
