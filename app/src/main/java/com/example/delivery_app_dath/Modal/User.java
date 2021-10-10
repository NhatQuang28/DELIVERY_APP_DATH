package com.example.delivery_app_dath.Modal;

public class User {
    private String name;
    private String phonenumber;
    private String email;
    private String password;

    public User() {
    }

    public User(String name,String phonenumber, String email, String password) {
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
}
