package com.example.textjson;

import android.graphics.Bitmap;

public class ListFoto {

    private String title;
    private int id;
    private String path_foto;
    private Bitmap bitmap;





    public ListFoto(String title, int id,String path_foto) {
        this.title = title;
        this.id = id;
        this.path_foto = path_foto;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }



    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getPath_foto() {
        return path_foto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
