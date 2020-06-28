package com.abdulrhman.springboot.api;

import com.abdulrhman.springboot.api.security.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseCotroller {


    public AppUser getCurrentUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (AppUser) authentication.getPrincipal();


    }

}
