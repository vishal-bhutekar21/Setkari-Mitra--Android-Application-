package com.example.Shetkari_Mitra;

public class Hospital_Info {

    private String Hospital_Name;
    private String Hospital_Number;
    private String  Hospital_Taluka;
    private String Hostpital_District;
    private String Hospital_Address;
    private  String Hospital_Contact_Person;




    public Hospital_Info(){}

    public Hospital_Info(String hospital_Name,String hospital_Contact_Person,
                         String hospital_Number,String hospital_Taluka,String hostpital_District,String hospital_Address)
    {
        this.Hospital_Name=hospital_Name;
        this.Hospital_Contact_Person=hospital_Contact_Person;
        this.Hospital_Number=hospital_Number;
        this.Hospital_Taluka=hospital_Taluka;
        this.Hostpital_District=hostpital_District;
        this.Hospital_Address=hospital_Address;
    }


    public String getHospital_Number() {
        return Hospital_Number;
    }

    public void setHospital_Number(String hospital_Number) {
        Hospital_Number = hospital_Number;
    }




    public String getHospital_Name() {
        return Hospital_Name;
    }

    public void setHospital_Name(String hospital_Name) {
        Hospital_Name = hospital_Name;
    }

    public String getHospital_Taluka() {
        return Hospital_Taluka;
    }

    public void setHospital_Taluka(String hospital_Taluka) {
        Hospital_Taluka = hospital_Taluka;
    }

    public String getHostpital_District() {
        return Hostpital_District;
    }

    public void setHostpital_District(String hostpital_District) {
        Hostpital_District = hostpital_District;
    }

    public String getHospital_Contact_Person() {
        return Hospital_Contact_Person;
    }

    public void setHospital_Contact_Person(String hospital_Contact_Person) {
        Hospital_Contact_Person = hospital_Contact_Person;
    }

    public String getHospital_Address() {
        return Hospital_Address;
    }

    public void setHospital_Address(String hospital_Address) {
        Hospital_Address = hospital_Address;
    }
}
