package ru.kata.spring.boot_security.demo.configs;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ADMIN")) {
            httpServletResponse.sendRedirect("/admin/allUsers");
            return;
        }

        if (roles.contains("USER")) {
            httpServletResponse.sendRedirect("/user");
            return;
        }

        httpServletResponse.sendRedirect("/");

    }
}

