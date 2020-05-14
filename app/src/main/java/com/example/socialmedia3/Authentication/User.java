package com.example.socialmedia3.Authentication;


public class User {
    public String coin, email, name, age, points, bio, instagram, snapchat, facebook;

    public User(){
        //Function Overloading (OOP) - can create functions with the same name
        // with differnt amounts of parameters or different types.
        //need a default constructor when you have a getter and setters
    }


    public User(String coin, String email, String name, String age, String points, String bio, String instagram, String snapchat, String facebook) {
        this.name = name;
        this.coin = coin;
        this.email = email;
        this.points = points;
        this.age = age;
        this.bio = bio;
        this.instagram = instagram;
        this.snapchat = snapchat;
        this.facebook = facebook;
    }

    //simple getter and setter functions to access variables
    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getSnapchat() {
        return snapchat;
    }

    public void setSnapchat(String snapchat) {
        this.snapchat = snapchat;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}

