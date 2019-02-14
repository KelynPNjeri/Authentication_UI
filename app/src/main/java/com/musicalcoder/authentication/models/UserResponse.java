package com.musicalcoder.authentication.models;

public class UserResponse {
    private Integer status;
    private String message;
    private RegisterUser registerUser;

    public UserResponse(Integer status, String message, RegisterUser registerUser) {
        this.status = status;
        this.message = message;
        this.registerUser = registerUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RegisterUser getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }
}

