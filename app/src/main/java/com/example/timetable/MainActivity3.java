package com.example.timetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

public class MainActivity3 extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    ArrayList<Specialty> specialtyArrayList;

    SpecialtyAdapter specialtyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();

        int id_dep = 0;
        id_dep = intent.getIntExtra("id",id_dep);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        specialtyArrayList = new ArrayList<>();

        specialtyAdapter= new SpecialtyAdapter(MainActivity3.this,specialtyArrayList, this);
        recyclerView.setAdapter(specialtyAdapter);

        getSpecialties(id_dep);
        
    }
    public void getSpecialties(int id_dep)
    {
        // Define the API endpoint URL
        String url = "http://num.univ-biskra.dz/psp/pspapi/specialty?department="+id_dep+"&semester=2&key=appmob";

        // Create a request queue
        RequestQueue queue2 = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.d("Response", "Array length: " + response.length()); // Log the length of the response
                            specialtyArrayList.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject spcObject = response.getJSONObject(i);
                                Specialty specialty = new Specialty(
                                        spcObject.getInt("id_specialty"),
                                        spcObject.getString("Nom_spec"),
                                        spcObject.getString("name_spec_ar")
                                );
                                specialtyArrayList.add(specialty);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run(){
                                        Log.d("JsonResponse3","specialty:"+specialty.toString());
                                    }
                                });
                            }

                            recyclerView.setAdapter(specialtyAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Log.e("Error", "Volley error: " + error.getMessage());
                    }
                });



        queue2.add(jsonArrayRequest);
    }

    @Override
    public void onItemClicked(Faculty faculty) {
        
    }

    @Override
    public void onItemClicked(Departement departement) {

    }

    @Override
    public void onItemClicked(Specialty specialty) {
        Intent intent= new Intent(MainActivity3.this,MainActivity4.class);
        intent.putExtra("id_spc",specialty.getId_specialty());
        startActivity(intent);

    }

    @Override
    public void onItemClicked(Levle levle) {

    }

    @Override
    public void onItemClicked(Section section) {

    }

    @Override
    public void onItemClicked(Group group) {

    }
}