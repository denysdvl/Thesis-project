package com.example.textjson;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    //private MapView mapView;
    private static String HI ;
    private RecyclerView rv;
    private List<Foto>list_data;
    private Double cor_x;
    private Double cor_y;
    private GoogleMap mMap;
    private MyAdapter adapter;
  private TextView textDesc;
private TextView textTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        rv=(RecyclerView)findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_data=new ArrayList<>();
textDesc=(TextView) findViewById(R.id.textDesc);
       textTitle =(TextView) findViewById(R.id.textTitle);
        adapter=new MyAdapter(list_data,this);
        String id = getIntent().getStringExtra("id");
        HI = ("http://192.168.1.3:3000/api/placeById?id_place="+id);
        getData();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    textTitle.setText(jsonObject.getString("title"));
                    textDesc.setText(jsonObject.getString("description"));
                    JSONArray array = jsonObject.getJSONArray("foto");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject ob = array.getJSONObject(i);

                        Foto ld = new Foto(ob.getString("title"), "http://192.168.1.3:3000/img/" + ob.getString("path_foto"));
                        list_data.add(ld);
                    }
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
        @Override
        public void onMapReady ( final GoogleMap googleMap){
            StringRequest request = new StringRequest(Request.Method.GET, HI, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        textTitle.setText(jsonObject.getString("title"));
                        cor_x = jsonObject.getDouble("coordinate_x");
                        cor_y = jsonObject.getDouble("coordinate_y");
                     //   System.out.println("--------------------------------------------------------------------------" + cor_x);
                        mMap = googleMap;
                      //  mMap.getUiSettings().setZoomControlsEnabled(false);
                      //  mMap.getUiSettings().setMyLocationButtonEnabled(true);
                        // Add a marker in Sydney and move the camera
                        LatLng sydney = new LatLng(cor_x, cor_y);
                        mMap.addMarker(new MarkerOptions().position(sydney).title(String.valueOf(textTitle)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(cor_x, cor_y), 15));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);

            // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }




}
