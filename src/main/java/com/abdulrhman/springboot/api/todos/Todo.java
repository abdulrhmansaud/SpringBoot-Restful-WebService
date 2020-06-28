package com.abdulrhman.springboot.api.todos;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "todo")
public class Todo {

    @Id
    private  String id;
    @NotNull(message="Title is Required ")
   @Size( min=3,message = "size most be at least 3 characers long")
    private  String title;
    @NotNull(message = "Descraption is required")
    private  String description;
    private  String author;
    private long timestamp;
    private String userId;

    public Todo() {
        this.timestamp = System.currentTimeMillis();
    }

    public Todo(String id, String title, String Description, String Author) {
        this.id = id;
        this.title = title;
        this.description = Description;
        this.author = Author;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() { return description; }

    public String getAuthor() { return author; }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }
}
