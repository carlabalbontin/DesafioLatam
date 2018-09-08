package com.desafiolatam.desafioface.models;

public class Developer extends SugarBase {

    private String name, email, twitter, github, photo_url;

    public Developer() {
    }

    public Developer(String name, String email, String twitter, String github, String photo_url) {
        this.name = name;
        this.email = email;
        this.twitter = twitter;
        this.github = github;
        this.photo_url = photo_url;
    }

    public String getTwitter() {
        return this.twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGithub() {
        return this.github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto_url() {
        return this.photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
