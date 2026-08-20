package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

public class LocalRescuerData {

    private static final List<Rescuer> rescuerList = new ArrayList<>();

    static {
        // ========== JALNA DISTRICT ==========
        rescuerList.add(new Rescuer("Rahul Shinde", "rahul.rescuer@gmail.com", "Jalna", "Jalna", "9822114455", "Old Jalna, Near Railway Station, Jalna"));
        rescuerList.add(new Rescuer("Vishal Gaikwad", "vishal.gaikwad.snake@gmail.com", "Jalna", "Jalna", "9881234567", "Devalgaon Raja Road, Jalna"));
        rescuerList.add(new Rescuer("Vikas Rathod", "vikas.snakes@gmail.com", "Jalna", "Ambad", "9422336677", "Ambad City, Near Bus Stand, Jalna"));
        rescuerList.add(new Rescuer("Anil Jadhav", "anil.wildlife@gmail.com", "Jalna", "Badnapur", "9977884411", "Station Road, Badnapur, Jalna"));
        rescuerList.add(new Rescuer("Gajanan Kale", "gajanan.rescuer@gmail.com", "Jalna", "Bhokardan", "9850123456", "Main Market, Bhokardan, Jalna"));
        rescuerList.add(new Rescuer("Sachin Pawar", "pawar.sachin@gmail.com", "Jalna", "Partur", "9766554433", "Partur Town, Near Sub-District Hospital, Jalna"));
        rescuerList.add(new Rescuer("Dnyaneshwar Gaikwad", "dnyaneshwar.g@gmail.com", "Jalna", "Jafrabad", "9890112233", "Jafrabad Central, Jalna"));
        rescuerList.add(new Rescuer("Mahesh Deshmukh", "mahesh.d@gmail.com", "Jalna", "Mantha", "9404556677", "Mantha Bus Stand Road, Jalna"));
        rescuerList.add(new Rescuer("Santosh Chavan", "santosh.chavan@gmail.com", "Jalna", "Ghansavangi", "9823445566", "Ghansavangi Market, Jalna"));
        rescuerList.add(new Rescuer("Prakash Solanke", "prakash.solanke@gmail.com", "Jalna", "Ambad", "9822998877", "Wadigodri Phata, Ambad, Jalna"));

        // ========== CHHATRAPATI SAMBHAJINAGAR (AURANGABAD) ==========
        rescuerList.add(new Rescuer("Akash More", "akash.more@aurangabadwild.org", "Chhatrapati Sambhajinagar", "Aurangabad", "9822001122", "CIDCO N-4, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Baba Shaikh", "baba.shaikh.snakes@gmail.com", "Chhatrapati Sambhajinagar", "Aurangabad", "9822338899", "Shahganj, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Anil Asaram Shirse", "anil.shirse@gmail.com", "Chhatrapati Sambhajinagar", "Aurangabad", "9422701122", "Waluj MIDC, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Pooja Kulkarni", "pooja.rescue@gmail.com", "Chhatrapati Sambhajinagar", "Paithan", "9823112233", "Near Nath Mandir, Paithan"));
        rescuerList.add(new Rescuer("Kailash Patil", "kailash.patil@gmail.com", "Chhatrapati Sambhajinagar", "Kannad", "9860123456", "Kannad Town, Chhatrapati Sambhajinagar"));

        // ========== BEED DISTRICT ==========
        rescuerList.add(new Rescuer("Balasaheb Shinde", "balasaheb.beed@gmail.com", "Beed", "Beed", "9422119988", "Nagar Road, Beed"));
        rescuerList.add(new Rescuer("Sunil Gavhane", "sunil.gavhane@gmail.com", "Beed", "Ambajogai", "9822443322", "Morewadi, Ambajogai, Beed"));
        rescuerList.add(new Rescuer("Ganesh Jagtap", "ganesh.jagtap@gmail.com", "Beed", "Georai", "9765443322", "National Highway 52, Georai, Beed"));

        // ========== PARBHANI & NANDED ==========
        rescuerList.add(new Rescuer("Shrikant Joshi", "shrikant.joshi@gmail.com", "Parbhani", "Parbhani", "9822556677", "Subhash Road, Parbhani"));
        rescuerList.add(new Rescuer("Dr. Nitin Kulkarni", "nitin.kulkarni.nanded@gmail.com", "Nanded", "Nanded", "9422176655", "VIP Road, Nanded"));
        rescuerList.add(new Rescuer("Maroti Maske", "maroti.maske@gmail.com", "Nanded", "Loha", "9860445566", "Loha Bypass, Nanded"));

        // ========== PUNE, NASHIK & WESTERN MAHARASHTRA ==========
        rescuerList.add(new Rescuer("RESQ Wildlife Helpline Pune", "info@resqct.org", "Pune", "Haveli", "9822055110", "Bavdhan, Pune, Maharashtra 411021"));
        rescuerList.add(new Rescuer("Aftab Kalindar Shaikh", "aftab.snakes@gmail.com", "Pune", "Haveli", "9822334455", "Katraj Snake Park Road, Pune"));
        rescuerList.add(new Rescuer("Vishal Bafna", "vishal.bafna@nashikwild.org", "Nashik", "Nashik", "9850667788", "Panchavati, Nashik, Maharashtra 422003"));
        rescuerList.add(new Rescuer("Ankit Chafekar", "ankit.snakes@gmail.com", "Nashik", "Nashik", "9890123450", "Gangapur Road, Nashik"));
        rescuerList.add(new Rescuer("Suhas Waingankar", "suhas.kolhapur@gmail.com", "Kolhapur", "Karveer", "9422045566", "Rajarampuri, Kolhapur"));
        rescuerList.add(new Rescuer("Amol Jadhav", "amol.jadhav@gmail.com", "Solapur", "Solapur", "9822119900", "Saat Rasta, Solapur"));
    }

    public static synchronized List<Rescuer> getRescuers() {
        return new ArrayList<>(rescuerList);
    }

    public static synchronized void addRescuer(Rescuer rescuer) {
        rescuerList.add(0, rescuer);
    }
}
