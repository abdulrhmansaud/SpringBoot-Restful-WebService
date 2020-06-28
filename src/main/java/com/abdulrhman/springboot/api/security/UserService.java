package com.abdulrhman.springboot.api.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Bean
    private PasswordEncoder passwordEncoder()
    {
            return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepository.findByEmail(username);

        if(user == null){

            throw  new UsernameNotFoundException("user not Found");
        }
        return user;

    }

    public AppUser save(@NotNull AppUser user) {

      user.setPassword(passwordEncoder().encode(user.getPassword()));
      return userRepository.save(user);

    }
        public List<AppUser> findAll() {
            return userRepository.findAll();
        }

}
