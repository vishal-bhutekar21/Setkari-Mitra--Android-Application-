package com.example.Shetkari_Mitra;

public class ChildItem {
    private int imageResourceId;
    private String description;

    public ChildItem(int imageResourceId, String description) {
        this.imageResourceId = imageResourceId;
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getDescription() {
        return description;
    }
}
