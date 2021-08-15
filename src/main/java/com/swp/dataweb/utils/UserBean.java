package com.swp.dataweb.utils;



public class UserBean {
    private int id;
    private String username;
    private String password;
    public UserBean(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
