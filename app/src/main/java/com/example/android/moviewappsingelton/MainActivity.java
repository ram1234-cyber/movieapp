package com.example.android.moviewappsingelton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private List<Movie> movieList;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        requestQueue=VolleySingelton.getmInstance(this).getRequestQueue();//singeltom me sab kuch krne ke bad
        movieList=new ArrayList<>();
        fetchMovies();


    }
    private void fetchMovies(){

        String url="https://www.json-generator.com/api/json/get/cePUMmxKIy?indent=2"; //array at the start

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {





                for (int i=0;i<response.length();i++) {

                    try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String overview = jsonObject.getString("overview");
                    String poster=jsonObject.getString("poster");
                    Double rating =jsonObject.getDouble("rating");

                    Movie movie = new Movie(title,overview,poster,rating);
                    movieList.add(movie);
                }  catch(JSONException e){
                    e.printStackTrace();
                    }

                    MovieAdapter adapter=new MovieAdapter(MainActivity.this,movieList);
                    recyclerView.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonArrayRequest);

    }
}