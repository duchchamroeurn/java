package com.sangkhim.spring.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private AntPathRequestMatcher unprotectedMatcher = new AntPathRequestMatcher("/api/admin/public/**", null);

    @Override
    public boolean matches(HttpServletRequest request) {          
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }
        return !unprotectedMatcher.matches(request);
    }
}
