package com.abdulrhman.springboot.api.todos;


import com.abdulrhman.springboot.api.BaseCotroller;
import com.abdulrhman.springboot.api.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class TodoController extends BaseCotroller {

    @Autowired
    private TodoService todoService;


    @GetMapping()
    public ResponseEntity<List<Todo>> listTodo() {
        List<Todo> result = todoService.findByuserId(getCurrentUser().getId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = { "/{id}" })
    public ResponseEntity<Todo> GetTodobyId(@PathVariable String id) {

        Todo result = todoService.getById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> CreateNewTodo(@RequestBody Todo todo) {
        todo.setUserId(getCurrentUser().getId());
        Todo result = todoService.save(todo);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable String id, @RequestBody Todo todo) {
        todo.setUserId(getCurrentUser().getId());
        Todo result = todoService.update(id,todo);
        return  new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    public ResponseEntity<Void> DeleteTodo(@PathVariable String id,Todo todo) {
        todo.setUserId(getCurrentUser().getId());
        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
