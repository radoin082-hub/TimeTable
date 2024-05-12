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

public class MainActivity5 extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    ArrayList<Section> sectionArrayList;

    SectionAdapter sectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);

        Intent intent = getIntent();

        int id_niv_spec = 0;
        id_niv_spec = intent.getIntExtra("id_niv_spec",id_niv_spec);

        recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        sectionArrayList=new ArrayList<>();

        sectionAdapter=new SectionAdapter(MainActivity5.this,sectionArrayList,this);
        recyclerView.setAdapter(sectionAdapter);
        getSectin(id_niv_spec);
    }

    private void getSectin(int id_niv_spec ){
        String url = "http://num.univ-biskra.dz/psp/pspapi/section?level_specialty="+id_niv_spec+"&semester=2&key=appmob";

        RequestQueue queue2 = VolleySingleton.getInstance(this).getRequestQueue();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {
                    Log.d("Response", "Array length: " + jsonArray.length());
                    sectionArrayList.clear();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject sectioObject = jsonArray.getJSONObject(i);
                        Section section = new Section(
                                sectioObject.getInt("sectionn_id"),
                                sectioObject.getString("Abrev_fr")
                        );
                        sectionArrayList.add(section);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run(){
                                Log.d("JsonResponse4","section:"+section.toString());
                            }
                        });
                    }
                    recyclerView.setAdapter(sectionAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error", "Volley error: " + volleyError.getMessage());
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
        Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
        intent.putExtra("section_id",section.getSectionn_id());
        startActivity(intent);

    }

    @Override
    public void onItemClicked(Group group) {

    }
}