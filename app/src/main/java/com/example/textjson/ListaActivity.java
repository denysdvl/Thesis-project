package com.example.textjson;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    private static final String URL_DATA = "http://192.168.1.3:3000/api/foto";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Bitmap> bitmaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        loadRecyclerViewDate();

    }

    private void loadRecyclerViewDate() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data... ");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ListItem>>() {
                            }.getType();
                            List<ListItem> items = gson.fromJson(s, type);
                            new LongOperation().execute(items);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setItems(List<ListItem> items){
        LinearLayoutManager manager = new LinearLayoutManager(ListaActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapterList(items, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.invalidate();
        adapter.notifyDataSetChanged();
    }

    private class LongOperation extends AsyncTask<List<ListItem>, Void, List<ListItem>> {


        @Override
        protected List<ListItem> doInBackground(List<ListItem>... listItems) {
            List<ListItem> raw = listItems[0];
            for (ListItem item : raw){
                try {
                    URL url = new URL("http://192.168.1.3:3000/img/"+item.getPath_foto());
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    item.setBitmap(bmp);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            return raw;
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}

        @Override
        protected void onPostExecute(List<ListItem> listItems) {
            setItems(listItems);
        }
    }

}
