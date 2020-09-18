package com.mbetemalu.mediapp2;

public class AppointmentHelper {

    private String doctor, date, time,description;

    public AppointmentHelper() {
    }

    public AppointmentHelper(String doctor, String date, String time, String description) {
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
