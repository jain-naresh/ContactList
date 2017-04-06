package com.example.contact;


public class UserModel implements  Comparable<UserModel>{

    private String name;
    private String id ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int compareTo(UserModel other)
    {
        return this.getName().toUpperCase().compareTo(other.getName().toUpperCase());
    }

}
