package com.example.timetable;

public class Faculty {
    int id_fac;
    String name_fac;

    String name_fac_ar;

    public Faculty(int id_fac, String name_fac, String name_fac_ar) {
        this.id_fac = id_fac;
        this.name_fac = name_fac;
        this.name_fac_ar = name_fac_ar;
    }

    public int getId_fac() {
        return id_fac;
    }

    public void setId_fac(int id_fac) {
        this.id_fac = id_fac;
    }

    public String getName_fac() {
        return name_fac;
    }

    public void setName_fac(String name_fac) {
        this.name_fac = name_fac;
    }

    public String getName_fac_ar() {
        return name_fac_ar;
    }

    public void setName_fac_ar(String name_fac_ar) {
        this.name_fac_ar = name_fac_ar;
    }
}
