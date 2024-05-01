package com.example.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SelectListener {

    TextView facultyNamesTextView;
    RecyclerView recyclerView;

    ArrayList<Faculty> facultyArrayList;

    FacultyAdapter facultyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize TextView
       facultyNamesTextView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.resview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        facultyArrayList = new ArrayList<>();
        // Request faculties from the API
        facultyAdapter = new FacultyAdapter(MainActivity.this, facultyArrayList,this);
        recyclerView.setAdapter(facultyAdapter);
        getFaculties();
    }

    public void getFaculties()
    {
        // Define the API endpoint URL
        String url = "https://num.univ-biskra.dz/psp/pspapi/faculty?key=appmob";

        // Create a request queue
        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.d("Response", "Array length: " + response.length()); // Log the length of the response
                                facultyArrayList.clear();
                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject facultyObject = response.getJSONObject(i);
                                    Faculty faculty = new Faculty(
                                            facultyObject.getInt("id_fac"),
                                            facultyObject.getString("name_fac"),
                                            facultyObject.getString("name_fac_ar")
                                    );
                                    facultyArrayList.add(faculty);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run(){
                                            Log.d("JsonResponse","faculty:"+faculty.toString());
                                        }
                                    });
                                }

                            //facultyNamesTextView.setText(facultyArrayList.get(1).id_fac);
                            // /ArrayAdapter<Faculty> facultyArrayAdapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,facultyArrayList);
                                recyclerView.setAdapter(facultyAdapter);

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



        queue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClicked(Faculty faculty) {
        Intent intent = new Intent(MainActivity.this , MainActivity2.class);
        intent.putExtra("name_fac",faculty.getName_fac());
        intent.putExtra("id_fac",faculty.getId_fac());
        startActivity(intent);
        /*if((faculty.getName_fac()).equals("Faculté des sciences exectes et sciences  de la natures et de la vie")){
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "no departements yet", Toast.LENGTH_SHORT).show();
        }*/

    }



    @Override
    public void onItemClicked(Departement departement) {

    }

    @Override
    public void onItemClicked(Specialty specialty) {

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
