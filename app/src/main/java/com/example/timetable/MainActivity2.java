package com.example.timetable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class MainActivity2 extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    ArrayList<Departement> departementArrayList;

    DepartementAdapter departementAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        int id_fac = 0;
        id_fac = intent.getIntExtra("id_fac",id_fac);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        departementArrayList= new ArrayList<>();

        departementAdapter=new DepartementAdapter((Context) MainActivity2.this,departementArrayList, this);
        recyclerView.setAdapter(departementAdapter);




            getDepartements(id_fac);


    }
    public void getDepartements(int id_fac)
    {
        // Define the API endpoint URL
        String url = "http://num.univ-biskra.dz/psp/pspapi/department?faculty="+id_fac+"&key=appmob";

        // Create a request queue
        RequestQueue queue2 = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.d("Response", "Array length: " + response.length()); // Log the length of the response
                            departementArrayList.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject depObject = response.getJSONObject(i);
                                Departement departement = new Departement(
                                        depObject.getString("name_fr")
                                );
                                departementArrayList.add(departement);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run(){
                                        Log.d("JsonResponse2","departemnt:"+departement.toString());
                                    }
                                });
                            }

                            recyclerView.setAdapter(departementAdapter);

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
}