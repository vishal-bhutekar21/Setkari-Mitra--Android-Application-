package com.example.Shetkari_Mitra;

public class DataClass {

    private String dataTitle;
    private String dataDesc;
    private String dataLang;
    private int dataImage;
    private String dataLocation;

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataLang() {
        return dataLang;
    }

    public String getDataLocation() {
        return dataLocation;
    }

    public int getDataImage() {
        return dataImage;
    }

    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataLocation, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataLang = dataLang;
        this.dataLocation = dataLocation;
        this.dataImage = dataImage;
    }
}
