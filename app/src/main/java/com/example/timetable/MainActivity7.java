package com.example.timetable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity7 extends AppCompatActivity {

    ArrayList<Schedule> scheduleArrayList;
    ArrayList<Schedule> sundayArrayList;
    ArrayList<Schedule> mondayArrayList;
    ArrayList<Schedule> tuesdayArrayList;
    ArrayList<Schedule> thursdayArrayList;
    ArrayList<Schedule> wednesdayArrayList;

    Button sunday;
    Button monday;
    Button tuesday;
    Button thursday;
    Button wednesday;
    Button saturday;
    Button allSession;

    RecyclerView recyclerView;

    ModuleAdapter moduleAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main7);

        sunday = findViewById(R.id.Sunday);
        monday = findViewById(R.id.Monday);
        tuesday = findViewById(R.id.Tuesday);
        wednesday = findViewById(R.id.Wednesday);
        thursday = findViewById(R.id.Thursday);
        saturday = findViewById(R.id.Saturday);
        allSession = findViewById(R.id.AllSession);

        Intent intent = getIntent();
        int groupe_id = 0;
        groupe_id = intent.getIntExtra("groupe_id",groupe_id);

        recyclerView = findViewById(R.id.recyclerView7);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        scheduleArrayList = new ArrayList<>();
        sundayArrayList = new ArrayList<>();
        mondayArrayList = new ArrayList<>();
        tuesdayArrayList = new ArrayList<>();
        wednesdayArrayList = new ArrayList<>();
        thursdayArrayList = new ArrayList<>();

        moduleAdapter = new ModuleAdapter(MainActivity7.this,scheduleArrayList);
        recyclerView.setAdapter(moduleAdapter);



        getSchedule(groupe_id);


        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sundayArrayList.isEmpty()) {
                    moduleAdapter = new ModuleAdapter(MainActivity7.this,sundayArrayList);
                    recyclerView.setAdapter(moduleAdapter);
                }
            }
        });

        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sundayArrayList.isEmpty()) {
                    moduleAdapter = new ModuleAdapter(MainActivity7.this,mondayArrayList);
                    recyclerView.setAdapter(moduleAdapter);
                }
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sundayArrayList.isEmpty()) {
                    moduleAdapter = new ModuleAdapter(MainActivity7.this,tuesdayArrayList);
                    recyclerView.setAdapter(moduleAdapter);
                }
            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sundayArrayList.isEmpty()) {
                    moduleAdapter = new ModuleAdapter(MainActivity7.this,wednesdayArrayList);
                    recyclerView.setAdapter(moduleAdapter);
                }
            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sundayArrayList.isEmpty()) {
                    moduleAdapter = new ModuleAdapter(MainActivity7.this,thursdayArrayList);
                    recyclerView.setAdapter(moduleAdapter);
                }
            }
        });


    }

    private void getSchedule(int groupe_id) {
        String url = "https://num.univ-biskra.dz/psp/emploi/section2_public?select_spec=109&niveau=3&section=521&groupe=" + groupe_id + "&sg=0&langu=fr&sem=2&id_year=2";
        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("Response", "Array length: " + response.length());
                            scheduleArrayList.clear();
                            sundayArrayList.clear();
                            mondayArrayList.clear();
                            tuesdayArrayList.clear();
                            wednesdayArrayList.clear();
                            thursdayArrayList.clear();

                            for (int i = 0; i < response.length(); i++) {
                                JSONArray jsonArray = response.getJSONArray(i);
                                Schedule schedule = new Schedule(
                                        jsonArray.optString(0),
                                        jsonArray.optString(1),
                                        jsonArray.optString(2),
                                        jsonArray.optString(5),
                                        jsonArray.optString(6),
                                        jsonArray.optString(8),
                                        jsonArray.optInt(12),
                                        jsonArray.optInt(13),
                                        jsonArray.optInt(21),
                                        jsonArray.optString(23),
                                        jsonArray.optString(24)
                                );
                                scheduleArrayList.add(schedule);
                                if (schedule.getDayOfWeek() == 1) { // Add schedules for Sunday to the sundayArrayList
                                    sundayArrayList.add(schedule);
                                }else if (schedule.getDayOfWeek()==2) {
                                    mondayArrayList.add(schedule);
                                } else if (schedule.getDayOfWeek()==3) {
                                    tuesdayArrayList.add(schedule);
                                } else if (schedule.getDayOfWeek()==4) {
                                    wednesdayArrayList.add(schedule);
                                } else if (schedule.getDayOfWeek()==5) {
                                    thursdayArrayList.add(schedule);
                                }
                            }
                            moduleAdapter.notifyDataSetChanged(); // Notify adapter of data change
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Volley error: " + error.getMessage());
                    }
                });
        queue.add(jsonArrayRequest);
    }


}