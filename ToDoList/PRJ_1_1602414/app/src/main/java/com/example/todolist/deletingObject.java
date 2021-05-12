package com.example.todolist;

public class deletingObject {

    String title;
    String post_id;

    public deletingObject(String title, String post_id) {
        this.title = title;
        this.post_id = post_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
