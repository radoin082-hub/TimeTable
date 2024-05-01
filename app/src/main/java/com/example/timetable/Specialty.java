package com.example.timetable;

public class Specialty {
    int id_specialty;
    String Nom_spec;
    String name_spec_ar;

    public Specialty(int id_specialty, String nom_spec, String name_spec_ar) {
        this.id_specialty = id_specialty;
        Nom_spec = nom_spec;
        this.name_spec_ar = name_spec_ar;
    }

    public int getId_specialty() {
        return id_specialty;
    }

    public void setId_specialty(int id_specialty) {
        this.id_specialty = id_specialty;
    }

    public String getNom_spec() {
        return Nom_spec;
    }

    public void setNom_spec(String nom_spec) {
        Nom_spec = nom_spec;
    }

    public String getName_spec_ar() {
        return name_spec_ar;
    }

    public void setName_spec_ar(String name_spec_ar) {
        this.name_spec_ar = name_spec_ar;
    }

    @Override
    public String toString() {
        return "Specialty{" +
                "id_specialty=" + id_specialty +
                ", Nom_spec='" + Nom_spec + '\'' +
                ", name_spec_ar='" + name_spec_ar + '\'' +
                '}';
    }

}
