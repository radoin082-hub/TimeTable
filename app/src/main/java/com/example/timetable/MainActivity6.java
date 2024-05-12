package com.example.timetable;

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

public class MainActivity6 extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    ArrayList<Group> groupArrayList;
    GroupAdapter groupAdapter;
    String id_niveau;
    int id_spc=0;
    int section_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main6);

        Intent intent = getIntent();

        section_id = 0;
        section_id = intent.getIntExtra("section_id",section_id);

        id_niveau = intent.getStringExtra("id_niveau");

        id_spc=0;
        id_spc = intent.getIntExtra("id_spc",id_spc);

        recyclerView = findViewById(R.id.recyclerView5);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groupArrayList = new ArrayList<>();

        groupAdapter= new GroupAdapter(MainActivity6.this,groupArrayList,this);
        recyclerView.setAdapter(groupAdapter);
        getGroup(section_id);
        recyclerView.setAdapter(groupAdapter);

    }
    private void getGroup(int section_id){
        // Define the API endpoint URL
        String url = "http://num.univ-biskra.dz/psp/pspapi/group?section="+section_id+"&semester=2&key=appmob";

        // Create a request queue
        RequestQueue queue2 = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            Log.d("Response", "Array length: " + response.length()); // Log the length of the response
                            groupArrayList.clear();
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject groupObject = response.getJSONObject(i);
                                Group group = new Group(
                                        groupObject.getInt("groupe_id"),
                                        groupObject.getString("groupe_name")

                                );
                                groupArrayList.add(group);
                                Log.d("Response", "Array length: " + response.length());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run(){
                                        Log.d("JsonResponse6","group:"+group.toString());
                                    }
                                });
                            }
                            groupAdapter.notifyDataSetChanged();

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

    }

    @Override
    public void onItemClicked(Section section) {

    }

    @Override
    public void onItemClicked(Group group) {
        Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
        intent.putExtra("groupe_id",group.getGroupe_id());
        intent.putExtra("id_spc",id_spc);
        intent.putExtra("id_niveau",id_niveau);
        intent.putExtra("section_id",section_id);
        startActivity(intent);

    }
}