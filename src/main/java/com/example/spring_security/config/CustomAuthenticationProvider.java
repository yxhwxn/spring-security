package com.example.spring_security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final InMemoryUserDetailsManager userDetailsManager;

    public CustomAuthenticationProvider(InMemoryUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        log.info("---[CustomAuthenticationProvider] 사용자 정보 조회 중: {}", username);

        UserDetails user = userDetailsManager.loadUserByUsername(username);

        if (!password.equals(user.getPassword())) { // 실제 프로젝트에서는 PasswordEncoder 사용 필요
            throw new BadCredentialsException("비밀번호가 틀립니다.");
        }

        log.info("---[CustomAuthenticationProvider] 인증 성공");
        return new UsernamePasswordAuthenticationToken(username, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
