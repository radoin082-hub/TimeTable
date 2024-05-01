package com.example.timetable;

public class Departement {
    int id;
    String name_fr;
    String name_ar;

    public Departement(int id, String name_fr, String name_ar) {
        this.id = id;
        this.name_fr = name_fr;
        this.name_ar = name_ar;
    }

    public Departement(String name_fr) {
        this.name_fr = name_fr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_fr() {
        return name_fr;
    }

    public void setName_fr(String name_fr) {
        this.name_fr = name_fr;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", name_fr='" + name_fr + '\'' +
                ", name_ar='" + name_ar + '\'' +
                '}';
    }
}
