package com.dw.member.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestUrl = request.getRequestURI();
        String httpMethod = request.getMethod();
        String userIP = request.getRemoteAddr();

        logger.info("요청 URL ========>" + requestUrl);
        logger.info("요청 HTTP 메소드 =======>" + httpMethod);
        logger.info("사용자 접속 IP =====>" + userIP);

        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}