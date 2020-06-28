package com.abdulrhman.springboot.api.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AppUser,String> {

    AppUser   findByEmail (String email);
}
