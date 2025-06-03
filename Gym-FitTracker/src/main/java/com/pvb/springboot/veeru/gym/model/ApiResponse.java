package com.pvb.springboot.veeru.gym.model;

public class ApiResponse {
    private String content;

    public ApiResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}