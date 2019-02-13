package com.musicalcoder.authentication;

import com.google.gson.annotations.SerializedName;

public class RegisterUser {
    @SerializedName("registered_user")
    private Registered_user registered_user;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;

    public Registered_user getRegistered_user() {
        return registered_user;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public static class Registered_user {
        @SerializedName("password2")
        private String password2;
        @SerializedName("password1")
        private String password1;
        @SerializedName("email")
        private String email;
        @SerializedName("username")
        private String username;
        @SerializedName("last_name")
        private String last_name;
        @SerializedName("first_name")
        private String first_name;

        public String getPassword2() {
            return password2;
        }

        public String getPassword1() {
            return password1;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getFirst_name() {
            return first_name;
        }
    }
}
