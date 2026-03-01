package com.duydev.backend.infrastructure.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j(topic = "CookieUtil")
@Component
@RequiredArgsConstructor
public class CookieUtil {
    // Set cookie
    public void setCookie(HttpServletResponse httpResponse, String name, String value, int maxAge) {
        log.info("Set cookie: name={}, value={}, maxAge={}", name, value, maxAge);
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        httpResponse.addCookie(cookie);
    }

    // Get cookie by name
    public <T> T getCookieValue(HttpServletRequest httpRequest, String name, Class<T> cls) {
        Cookie[] cookies = httpRequest.getCookies();
        T object = null;
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                try {
                    object = SerializationObjectUtil.deserialize(cookie.getValue(), cls);
                } catch (Exception e) {
                    log.error("Error deserializing cookie value: {}", e.getMessage());
                    return null;
                }
                break;
            }
        }
        return object;
    }

    // Delete cookie by name
    public void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                cookie.setValue(null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
    }
}
