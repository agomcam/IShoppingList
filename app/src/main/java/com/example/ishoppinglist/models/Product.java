package com.example.ishoppinglist.models;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String name;
    String note;
    boolean state;
    boolean lactosa;
    boolean gluten;

    public Product() {
    }

    public Product(int id, String name, String note, boolean state, boolean lactosa, boolean gluten) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.state = state;
        this.lactosa = lactosa;
        this.gluten = gluten;
    }

    public boolean isLactosa() {
        return lactosa;
    }

    public void setLactosa(boolean lactosa) {
        this.lactosa = lactosa;
    }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", note='" + note + '\'' +
                ", state=" + state +
                '}';
    }
}
