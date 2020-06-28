package com.abdulrhman.springboot.api.home;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @RequestMapping(value = "/")
    public String Greating(){
        return "Welcome To Our Spring Boot !!!!";
    }

    @GetMapping(value = "/{name}")
    public  String GreatingWithName(@PathVariable String name){

        return  String.format("welcome %s to Our Spring Boot",name);

    }
}
