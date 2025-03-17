package com.example.spring_security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SecurityFilterLogger implements ApplicationListener<ContextRefreshedEvent> {

    private final FilterChainProxy filterChainProxy;

    public SecurityFilterLogger(FilterChainProxy filterChainProxy) {
        this.filterChainProxy = filterChainProxy;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(">>>>[SecurityFilterLogger] 등록된 Security 필터 목록:");
        filterChainProxy.getFilterChains().forEach(chain ->
                chain.getFilters().forEach(filter -> log.info(">>>>>>>>{}", filter.getClass().getName()))
        );
    }
}
