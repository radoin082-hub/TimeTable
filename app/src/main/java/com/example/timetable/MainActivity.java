package com.example.timetable;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    TextView facultyNamesTextView;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize TextView
        facultyNamesTextView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.resview);
        // Request faculties from the API
        getFaculties();
    }

    private void getFaculties() {
        // Define the API endpoint URL
        String url = "http://num.univ-biskra.dz/psp/pspapi/faculty?key=appmob";

        // Create a request queue
        RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            //Log.d("Response", "Array length: " + response.length()); // Log the length of the response
                            if (response.length() == 0) {
                                Log.d("Response", "Response is empty");

                                facultyNamesTextView.setText("response is empty");
                            } else {
                                ArrayList<Faculty> facultyArrayList = new ArrayList<>();
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
                                
                                recyclerView.setAdapter(new FacultyAdapter(MainActivity.this, facultyArrayList));
                            }
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
}
