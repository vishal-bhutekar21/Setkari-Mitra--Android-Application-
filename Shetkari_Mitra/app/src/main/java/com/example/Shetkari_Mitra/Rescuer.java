package com.example.Shetkari_Mitra;

public class Rescuer {
    private String name;
    private String email;
    private String district;
    private String taluka;
    private String mobile;
    private String address;

    public Rescuer() {

    }

    public Rescuer(String name, String email, String district, String taluka, String mobile, String address) {
        this.name = name;
        this.email = email;
        this.district = district;
        this.taluka = taluka;
        this.mobile = mobile;
        this.address = address;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDistrict() {
        return district;
    }

    public String getTaluka() {
        return taluka;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
