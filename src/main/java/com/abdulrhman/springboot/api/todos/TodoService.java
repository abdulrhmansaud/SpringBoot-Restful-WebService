package com.abdulrhman.springboot.api.todos;

import com.abdulrhman.springboot.api.error.ConflictException;
import com.abdulrhman.springboot.api.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    @Autowired
    public TodoRepository todoRepository;


    public List<Todo> findall() {
        return todoRepository.findAll();
    }


    public List<Todo> findByuserId(String id){

        return todoRepository.findByuserId(id);

    }

    public Todo getById(String id){

        try{
            return todoRepository.findById(id).get();
        }catch (NoSuchElementException ex){
       throw  new NotFoundException(String.format("No Record with the id [%s] was found in our Database ",id));
        }
    }

    public Todo save(Todo todo) {

       if(todoRepository.findByTitle(todo.getTitle()) != null) {

           throw new ConflictException("Another Record with the same title exists");
       }
           return todoRepository.insert(todo);
    }

    public Todo update(String id, Todo todo) {
        Todo updated = getById(id);
        updated.setId(todo.getId());
        updated.setTitle(todo.getTitle());
        updated.setDescription(todo.getDescription());
        updated.setAuthor(todo.getAuthor());
        return todoRepository.save(updated);
    }

    public void delete(String id) {

        todoRepository.deleteById(id);
    }
}


