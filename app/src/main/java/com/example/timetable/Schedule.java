package com.example.timetable;

public class Schedule {
    String ClassName;
    String Location;
    String TypeOfCourse;
    String ProfessorLastName;
    String ProfessorFirstName;
    String ModuleName;
    int DayOfWeek;
    int TimeSlot;
    int Online;
    String OnlineLink;
    String LocationGPS;

    public Schedule(String className, String location, String typeOfCourse, String professorLastName, String professorFirstName, String moduleName, int dayOfWeek, int timeSlot, int online, String onlineLink, String locationGPS) {
        ClassName = className;
        Location = location;
        TypeOfCourse = typeOfCourse;
        ProfessorLastName = professorLastName;
        ProfessorFirstName = professorFirstName;
        ModuleName = moduleName;
        DayOfWeek = dayOfWeek;
        TimeSlot = timeSlot;
        Online = online;
        OnlineLink = onlineLink;
        LocationGPS = locationGPS;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTypeOfCourse() {
        return TypeOfCourse;
    }

    public void setTypeOfCourse(String typeOfCourse) {
        TypeOfCourse = typeOfCourse;
    }

    public String getProfessorLastName() {
        return ProfessorLastName;
    }

    public void setProfessorLastName(String professorLastName) {
        ProfessorLastName = professorLastName;
    }

    public String getProfessorFirstName() {
        return ProfessorFirstName;
    }

    public void setProfessorFirstName(String professorFirstName) {
        ProfessorFirstName = professorFirstName;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public int getDayOfWeek() {
        return DayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        DayOfWeek = dayOfWeek;
    }

    public int getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        TimeSlot = timeSlot;
    }

    public int getOnline() {
        return Online;
    }

    public void setOnline(int online) {
        Online = online;
    }

    public String getOnlineLink() {
        return OnlineLink;
    }

    public void setOnlineLink(String onlineLink) {
        OnlineLink = onlineLink;
    }

    public String getLocationGPS() {
        return LocationGPS;
    }

    public void setLocationGPS(String locationGPS) {
        LocationGPS = locationGPS;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "ClassName='" + ClassName + '\'' +
                ", Location='" + Location + '\'' +
                ", TypeOfCourse='" + TypeOfCourse + '\'' +
                ", ProfessorLastName='" + ProfessorLastName + '\'' +
                ", ProfessorFirstName='" + ProfessorFirstName + '\'' +
                ", ModuleName='" + ModuleName + '\'' +
                ", DayOfWeek=" + DayOfWeek +
                ", TimeSlot=" + TimeSlot +
                ", Online=" + Online +
                ", OnlineLink='" + OnlineLink + '\'' +
                ", LocationGPS='" + LocationGPS + '\'' +
                '}';
    }
}
