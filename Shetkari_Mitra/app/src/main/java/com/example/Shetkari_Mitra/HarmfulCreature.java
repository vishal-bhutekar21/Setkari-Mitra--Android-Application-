package com.example.Shetkari_Mitra;

import java.io.Serializable;

public class HarmfulCreature implements Serializable {
    private String nameEn;
    private String nameMr;
    private String scientificName;
    private String category; // Scorpion, Centipede, Beetle, Wasp/Bee, Caterpillar, Spider
    private String dangerLevel; // High (अतिधोकादायक), Moderate (मध्यम धोका), Low (कमी धोका)
    private String toxicityTypeEn;
    private String toxicityTypeMr;
    private String identificationEn;
    private String identificationMr;
    private String habitatEn;
    private String habitatMr;
    private String preventionEn;
    private String preventionMr;
    private String firstAidEn;
    private String firstAidMr;
    private int imageResId;

    public HarmfulCreature(String nameEn, String nameMr, String scientificName, String category,
                           String dangerLevel, String toxicityTypeEn, String toxicityTypeMr,
                           String identificationEn, String identificationMr,
                           String habitatEn, String habitatMr,
                           String preventionEn, String preventionMr,
                           String firstAidEn, String firstAidMr,
                           int imageResId) {
        this.nameEn = nameEn;
        this.nameMr = nameMr;
        this.scientificName = scientificName;
        this.category = category;
        this.dangerLevel = dangerLevel;
        this.toxicityTypeEn = toxicityTypeEn;
        this.toxicityTypeMr = toxicityTypeMr;
        this.identificationEn = identificationEn;
        this.identificationMr = identificationMr;
        this.habitatEn = habitatEn;
        this.habitatMr = habitatMr;
        this.preventionEn = preventionEn;
        this.preventionMr = preventionMr;
        this.firstAidEn = firstAidEn;
        this.firstAidMr = firstAidMr;
        this.imageResId = imageResId;
    }

    public String getNameEn() { return nameEn; }
    public String getNameMr() { return nameMr; }
    public String getScientificName() { return scientificName; }
    public String getCategory() { return category; }
    public String getDangerLevel() { return dangerLevel; }
    public String getToxicityTypeEn() { return toxicityTypeEn; }
    public String getToxicityTypeMr() { return toxicityTypeMr; }
    public String getIdentificationEn() { return identificationEn; }
    public String getIdentificationMr() { return identificationMr; }
    public String getHabitatEn() { return habitatEn; }
    public String getHabitatMr() { return habitatMr; }
    public String getPreventionEn() { return preventionEn; }
    public String getPreventionMr() { return preventionMr; }
    public String getFirstAidEn() { return firstAidEn; }
    public String getFirstAidMr() { return firstAidMr; }
    public int getImageResId() { return imageResId; }
}
