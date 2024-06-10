package com.example.thethingsproject;

public class Donor {

    private String username;
    private String password;

    private String currentLocation;

    private String bloodGroup;

    private String city;

    public Donor(String username, String password, String bloodGroup) {
        this.username = username;
        this.password = password;
        this.bloodGroup = bloodGroup;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCurrentLocation() {
        return this.currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

}
