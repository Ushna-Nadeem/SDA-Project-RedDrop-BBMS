package com.example.thethingsproject;

public class Reaction {
    private String username;
    private String reaction;

    private String date;

    public Reaction(String username, String reaction, String date) {
        this.username = username;
        this.reaction = reaction;
        this.date = date;
    }

    public String getUsername() {
        return this.username;
    }

    public String getReaction() {
        return this.reaction;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
