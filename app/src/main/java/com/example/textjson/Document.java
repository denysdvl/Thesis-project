package com.example.textjson;

import java.util.List;

public class Document {


    private Double coordinate_x;

    private Double coordinate_y;

    private String title;

    private String description;

    private Foto[] foto;

    public Double getCoordinateX() {
        return coordinate_x;
    }

    public void setCoordinateX(Double coordinate_x) {
        this.coordinate_x = coordinate_x;
    }

    public Double getCoordinateY() {
        return coordinate_y;
    }

    public void setCoordinateY(Double coordinate_y) {
        this.coordinate_y = coordinate_y;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Foto[] getFoto() {
        return foto;
    }

    public void setFoto(Foto[] foto) {
        this.foto = foto;
    }
}
