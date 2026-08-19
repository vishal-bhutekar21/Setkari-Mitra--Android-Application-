package com.example.Shetkari_Mitra;

public class EmergencyContact {
    private int id;
    private String name;
    private String number;

    public EmergencyContact() {}

    public EmergencyContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public EmergencyContact(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    // Convert from Room entity
    public static EmergencyContact fromEntity(EmergencyContactEntity entity) {
        return new EmergencyContact(entity.id, entity.name, entity.phoneNumber);
    }

    // Convert to Room entity
    public EmergencyContactEntity toEntity() {
        EmergencyContactEntity entity = new EmergencyContactEntity(name, number);
        entity.id = id;
        return entity;
    }
}
