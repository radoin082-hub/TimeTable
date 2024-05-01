package com.example.timetable;

public class Group {

    int groupe_id;
    String groupe_name;

    public Group(int groupe_id, String groupe_name) {
        this.groupe_id = groupe_id;
        this.groupe_name = groupe_name;
    }

    public int getGroupe_id() {
        return groupe_id;
    }

    public void setGroupe_id(int groupe_id) {
        this.groupe_id = groupe_id;
    }

    public String getGroupe_name() {
        return groupe_name;
    }

    public void setGroupe_name(String groupe_name) {
        this.groupe_name = groupe_name;
    }

    @Override
    public String toString() {
        return "GroupAdapter{" +
                "groupe_id=" + groupe_id +
                ", groupe_name='" + groupe_name + '\'' +
                '}';
    }
}
