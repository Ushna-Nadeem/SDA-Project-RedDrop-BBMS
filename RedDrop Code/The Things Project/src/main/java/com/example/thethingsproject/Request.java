package com.example.thethingsproject;

public class Request {
    private String city;
    private String location;
    private String bloodGroup;

    private boolean emergency;

    public Request(String city, String location, String bloodGroup, boolean emergency) {
        this.city = city;
        this.location = location;
        this.bloodGroup = bloodGroup;
        this.emergency = emergency;
    }

    public String getCity() {
        return this.city;
    }

    public String getLocation() {
        return this.location;
    }

    public String getBloodGroup() {
        return this.bloodGroup;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public boolean getEmergency() {
        return this.emergency;
    }


}
