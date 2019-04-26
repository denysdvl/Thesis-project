package com.example.textjson;

import android.graphics.Bitmap;

public class ListItem {

    private String title;
    private int id_place;
    private String path_foto;
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ListItem(String title, int id_place, String path_foto) {
        this.title = title;
        this.id_place = id_place;
        this.path_foto = path_foto;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id_place;
    }

    public String getPath_foto() {
        return path_foto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
