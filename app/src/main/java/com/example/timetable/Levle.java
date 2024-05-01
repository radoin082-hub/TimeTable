package com.example.timetable;

public class Levle {
    int id_niv_spec;
    String  id_niveau;

    public Levle(int id_niv_spec, String id_nivo) {
        this.id_niv_spec = id_niv_spec;
        this.id_niveau = id_nivo;
    }

    public int getId_niv_spec() {
        return id_niv_spec;
    }

    public void setId_niv_spec(int id_niv_spec) {
        this.id_niv_spec = id_niv_spec;
    }

    public String getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(String id_niveau) {
        this.id_niveau = id_niveau;
    }

    @Override
    public String toString() {
        return "Levle{" +
                "id_niv_spec=" + id_niv_spec +
                ", id_niveau='" + id_niveau + '\'' +
                '}';
    }
}
