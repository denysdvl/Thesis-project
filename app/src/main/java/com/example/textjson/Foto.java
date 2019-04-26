package com.example.textjson;

import android.graphics.Bitmap;

public class Foto {

    private String title;

    private String path_foto;
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Foto(String title,  String path_foto) {
        this.title = title;

        this.path_foto = path_foto;
    }

    public String getTitle() {
        return title;
    }



    public String getPath_foto() {
        return path_foto;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
