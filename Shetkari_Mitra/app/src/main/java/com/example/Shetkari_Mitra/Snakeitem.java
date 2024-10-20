package com.example.Shetkari_Mitra;

public class Snakeitem {
    private String symtomsName;
    private String snakeinformation;
    private int symtomsImage;

    public String getSymtomsName() {
        return symtomsName;
    }

    public String getSnakeinformation()
    {
        return snakeinformation;
    }

    public int getSymtomsImage() {
        return symtomsImage;
    }


    public Snakeitem(String symtomsName, String snakeinformation, int symtomsImage) {
        this.symtomsName = symtomsName;
        this.snakeinformation=snakeinformation;
        this.symtomsImage = symtomsImage;
    }
}
