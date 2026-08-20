package com.example.Shetkari_Mitra;

import java.io.Serializable;

public class DataClass implements Serializable {

    private String nameEn;
    private String nameMr;
    private String scientificName;
    private String venomStatusEn;
    private String venomStatusMr;
    private String venomTypeEn;
    private String venomTypeMr;
    private String descEn;
    private String descMr;
    private String identificationEn;
    private String identificationMr;
    private String habitatEn;
    private String habitatMr;
    private String dietEn;
    private String dietMr;
    private String safetyTipsEn;
    private String safetyTipsMr;
    private String firstAidEn;
    private String firstAidMr;
    private int imageRes;
    private int venomLevel; // 0 = Non-venomous, 1 = Highly Venomous, 2 = Mildly Venomous

    public DataClass(String nameEn, String nameMr, String scientificName,
                     String venomStatusEn, String venomStatusMr,
                     String venomTypeEn, String venomTypeMr,
                     String descEn, String descMr,
                     String identificationEn, String identificationMr,
                     String habitatEn, String habitatMr,
                     String dietEn, String dietMr,
                     String safetyTipsEn, String safetyTipsMr,
                     String firstAidEn, String firstAidMr,
                     int imageRes, int venomLevel) {
        this.nameEn = nameEn;
        this.nameMr = nameMr;
        this.scientificName = scientificName;
        this.venomStatusEn = venomStatusEn;
        this.venomStatusMr = venomStatusMr;
        this.venomTypeEn = venomTypeEn;
        this.venomTypeMr = venomTypeMr;
        this.descEn = descEn;
        this.descMr = descMr;
        this.identificationEn = identificationEn;
        this.identificationMr = identificationMr;
        this.habitatEn = habitatEn;
        this.habitatMr = habitatMr;
        this.dietEn = dietEn;
        this.dietMr = dietMr;
        this.safetyTipsEn = safetyTipsEn;
        this.safetyTipsMr = safetyTipsMr;
        this.firstAidEn = firstAidEn;
        this.firstAidMr = firstAidMr;
        this.imageRes = imageRes;
        this.venomLevel = venomLevel;
    }

    // Compatibility getters for legacy callers
    public String getDataTitle() {
        return nameEn + " (" + nameMr + ")";
    }

    public String getDataDesc() {
        return descEn;
    }

    public String getDataLang() {
        return venomStatusEn;
    }

    public String getDataLocation() {
        return habitatEn;
    }

    public int getDataImage() {
        return imageRes;
    }

    // Bilingual Dynamic Getters
    public String getName(boolean isMarathi) {
        return isMarathi ? nameMr : nameEn;
    }

    public String getVenomStatus(boolean isMarathi) {
        return isMarathi ? venomStatusMr : venomStatusEn;
    }

    public String getVenomType(boolean isMarathi) {
        return isMarathi ? venomTypeMr : venomTypeEn;
    }

    public String getDesc(boolean isMarathi) {
        return isMarathi ? descMr : descEn;
    }

    public String getIdentification(boolean isMarathi) {
        return isMarathi ? identificationMr : identificationEn;
    }

    public String getHabitat(boolean isMarathi) {
        return isMarathi ? habitatMr : habitatEn;
    }

    public String getDiet(boolean isMarathi) {
        return isMarathi ? dietMr : dietEn;
    }

    public String getSafetyTips(boolean isMarathi) {
        return isMarathi ? safetyTipsMr : safetyTipsEn;
    }

    public String getFirstAid(boolean isMarathi) {
        return isMarathi ? firstAidMr : firstAidEn;
    }

    public String getNameEn() { return nameEn; }
    public String getNameMr() { return nameMr; }
    public String getScientificName() { return scientificName; }
    public String getVenomStatusEn() { return venomStatusEn; }
    public String getVenomStatusMr() { return venomStatusMr; }
    public String getVenomTypeEn() { return venomTypeEn; }
    public String getVenomTypeMr() { return venomTypeMr; }
    public String getDescEn() { return descEn; }
    public String getDescMr() { return descMr; }
    public String getIdentificationEn() { return identificationEn; }
    public String getIdentificationMr() { return identificationMr; }
    public String getHabitatEn() { return habitatEn; }
    public String getHabitatMr() { return habitatMr; }
    public String getDietEn() { return dietEn; }
    public String getDietMr() { return dietMr; }
    public String getSafetyTipsEn() { return safetyTipsEn; }
    public String getSafetyTipsMr() { return safetyTipsMr; }
    public String getFirstAidEn() { return firstAidEn; }
    public String getFirstAidMr() { return firstAidMr; }
    public int getImageRes() { return imageRes; }
    public int getVenomLevel() { return venomLevel; }
}
