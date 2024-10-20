package com.example.Shetkari_Mitra;

public class Registration {

    private String name;
    private String email;
    private String mobile;
    private String address;
    private String pincode;
    private String dob;
    private String district;
    private String taluka;

    // Required default constructor for Firebase's DataSnapshot.getValue method
    public Registration() {
    }

    public Registration(String name, String email, String mobile, String address, String pincode, String dob, String taluka, String district) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.pincode = pincode;
        this.dob = dob;
        this.district = district;
        this.taluka = taluka;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }
}
