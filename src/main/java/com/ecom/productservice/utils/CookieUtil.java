package com.ecom.productservice.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

public final class CookieUtil {

    public static String getSessionIdFromRequest(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "sessionId");
        return cookie != null ? cookie.getValue() : null;
    }
}
