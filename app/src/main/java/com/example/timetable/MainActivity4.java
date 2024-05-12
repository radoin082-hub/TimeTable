package com.example.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;

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

public class MainActivity4 extends AppCompatActivity implements SelectListener {
    RecyclerView recyclerView;
    ArrayList<Levle> levleArrayList;

    LevelAdapter levelAdapter;
    int id_spc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();

        id_spc = 0;
        id_spc = intent.getIntExtra("id_spc",id_spc);



        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        levleArrayList = new ArrayList<>();

        levelAdapter= new LevelAdapter(MainActivity4.this,levleArrayList, this);
        recyclerView.setAdapter(levelAdapter);

        getLevles(id_spc);

    }
    public void getLevles(int id_spc)
    {
        // Define the API endpoint URL
        String url = "http://num.univ-biskra.dz/psp/pspapi/level?specialty="+id_spc+"&semester=2&key=appmob";

        // Create a request queue
        RequestQueue queue2 = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.d("Response", "Array length: " + response.length()); // Log the length of the response
                            levleArrayList.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject levelObject = response.getJSONObject(i);
                                Levle levle = new Levle(
                                        levelObject.getInt("id_niv_spec"),
                                        levelObject.getString("id_niveau")

                                );
                                levleArrayList.add(levle);
                                Log.d("Response", "Array length: " + response.length());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run(){
                                        Log.d("JsonResponse4","levle:"+levle.toString());
                                    }
                                });
                            }

                            recyclerView.setAdapter(levelAdapter);

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

    }

    @Override
    public void onItemClicked(Levle levle) {
        Intent intent= new Intent(MainActivity4.this,MainActivity5.class);
        intent.putExtra("id_niv_spec",levle.getId_niv_spec());
        intent.putExtra("id_spc",id_spc);
        intent.putExtra("id_niveau",levle.getId_niveau());
        startActivity(intent);
    }

    @Override
    public void onItemClicked(Section section) {



    }

    @Override
    public void onItemClicked(Group group) {

    }
}