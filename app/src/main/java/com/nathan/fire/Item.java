package com.nathan.fire;


public class Item {
    String username,fullnames,email,password,id;

    public Item(String username, String fullnames, String email, String password, String id) {
        this.username = username;
        this.fullnames = fullnames;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public Item() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
