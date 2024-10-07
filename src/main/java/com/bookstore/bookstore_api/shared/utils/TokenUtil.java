package com.bookstore.bookstore_api.shared.utils;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {
    public static String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
