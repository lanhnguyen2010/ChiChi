package com.chichi.util;

import com.chichi.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lanhnguyen on 01/11/2016.
 */
public final class SecurityUtil {
    private SecurityUtil(){

    }

    public static User getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null && principal instanceof User){
            return (User)principal;
        }
        return null;
    }
}
