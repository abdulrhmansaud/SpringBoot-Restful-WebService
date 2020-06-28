package com.abdulrhman.springboot.api.util;

import com.abdulrhman.springboot.api.security.AppUser;
import com.abdulrhman.springboot.api.security.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class FirstTimeInitializer implements CommandLineRunner {


   private final Log logger = LogFactory.getLog(FirstTimeInitializer.class);


    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.findAll().isEmpty()){

            logger.info("No user Account found , Creating some user");


            AppUser user = new AppUser("abd1@gmail.xom","password","abd");
            userService.save(user);
        }
        // check if we have user
        // if no user exist , create some user :)
    }
}
