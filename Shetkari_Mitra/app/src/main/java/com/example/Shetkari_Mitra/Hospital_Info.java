package com.example.Shetkari_Mitra;

public class Hospital_Info {
    private String hospitalName;
    private String contactPerson;
    private String hospitalNumber;
    private String taluka;
    private String district;
    private String address;

    public Hospital_Info() {
        // Default constructor required for data deserialization
    }

    public Hospital_Info(String hospitalName, String contactPerson, String hospitalNumber,
                         String taluka, String district, String address) {
        this.hospitalName = hospitalName;
        this.contactPerson = contactPerson;
        this.hospitalNumber = hospitalNumber;
        this.taluka = taluka;
        this.district = district;
        this.address = address;
    }

    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getHospitalNumber() { return hospitalNumber; }
    public void setHospitalNumber(String hospitalNumber) { this.hospitalNumber = hospitalNumber; }

    public String getTaluka() { return taluka; }
    public void setTaluka(String taluka) { this.taluka = taluka; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
