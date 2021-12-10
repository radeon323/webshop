package com.luxoft.oleksandr_shevchenko.webshop.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SecurityService {

    public static boolean isAuth(HttpServletRequest req, List<String> userTokens) {
        Cookie[] cookies = req.getCookies();
        if(cookies !=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("user-token")) {
                    if(userTokens.contains(cookie.getValue())) {
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }


}
