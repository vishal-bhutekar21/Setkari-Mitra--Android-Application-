package com.example.Shetkari_Mitra;

public class EmergencyContact {
    private String name;
    private String number;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private  String id;
    public EmergencyContact() {
    }

    public EmergencyContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}
