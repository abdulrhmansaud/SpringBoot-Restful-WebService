package com.abdulrhman.springboot.api.todos;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoRepository extends MongoRepository<Todo,String> {

    Todo findByTitle(String title);

    List<Todo> findByuserId(String userId);
}
