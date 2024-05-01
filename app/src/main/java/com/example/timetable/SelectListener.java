package com.example.timetable;

public interface SelectListener {
    void onItemClicked(Faculty faculty);
    void onItemClicked(Departement departement);
    void onItemClicked(Specialty specialty);
    void onItemClicked(Levle levle);

    void onItemClicked(Section section);
    void onItemClicked(Group group);


}
