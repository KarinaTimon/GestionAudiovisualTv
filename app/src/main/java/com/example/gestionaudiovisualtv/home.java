package com.example.gestionaudiovisualtv;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gestionaudiovisualtv.adapter.Adapter;
import com.example.gestionaudiovisualtv.model.Proyecto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class home extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static final String Url_proyecto = "http://192.168.1.11/gestionaudiovisual/getItemsDB.php";
    private SearchView svSearch;
    private Adapter adapter;

    List<Proyecto> proyectoList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager( this));

        proyectoList = new ArrayList<>();
        svSearch = (SearchView)findViewById(R.id.svSearch);

        loadproyect();
        initListener();
    }

    private void loadproyect(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url_proyecto, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject proyecto = array.getJSONObject(i);

                        int activoAux = proyecto.getInt("activo");

                        boolean esActivo = true;
                        if (activoAux == 0){
                            esActivo = false;
                        }

                        String strfechaInicio = proyecto.getString("fechaini");
                        String strfechaFin  = proyecto.getString("fechafin");

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaInicio = sdf.parse(strfechaInicio);
                        Date fechaFin = sdf.parse(strfechaFin);

                        proyectoList.add(new Proyecto(
                                proyecto.getInt("id"),
                                proyecto.getString("nombre"),
                                fechaInicio,
                                fechaFin,
                                proyecto.getString("desc"),
                                proyecto.getInt("presu"),
                                esActivo
                        ));
                    }

                    adapter = new Adapter(home.this, proyectoList);//Adapter
                    recyclerView.setAdapter(adapter);
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void initListener(){
        svSearch.setOnQueryTextListener(this);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filterName(newText);
        return false;
    }
}



