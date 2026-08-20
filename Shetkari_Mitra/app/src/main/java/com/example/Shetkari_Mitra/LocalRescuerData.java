package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

public class LocalRescuerData {

    private static final List<Rescuer> rescuerList = new ArrayList<>();

    static {
        // ========== JALNA DISTRICT ==========
        rescuerList.add(new Rescuer("Rahul Shinde (Certified Sarpa Mitra)", "rahul.rescuer@gmail.com", "Jalna", "Jalna", "9822114455", "Old Jalna, Near Railway Station, Jalna"));
        rescuerList.add(new Rescuer("Vishal Gaikwad (Sarpmitra Sangh)", "vishal.gaikwad.snake@gmail.com", "Jalna", "Jalna", "9881234567", "Devalgaon Raja Road, Jalna"));
        rescuerList.add(new Rescuer("Vikas Rathod (Wildlife Rescuer)", "vikas.snakes@gmail.com", "Jalna", "Ambad", "9422336677", "Ambad City, Near Bus Stand, Jalna"));
        rescuerList.add(new Rescuer("Anil Jadhav (Sarpa Mitra)", "anil.wildlife@gmail.com", "Jalna", "Badnapur", "9977884411", "Station Road, Badnapur, Jalna"));
        rescuerList.add(new Rescuer("Gajanan Kale (Sarpa Mitra)", "gajanan.rescuer@gmail.com", "Jalna", "Bhokardan", "9850123456", "Main Market, Bhokardan, Jalna"));
        rescuerList.add(new Rescuer("Sachin Pawar (Sarpa Mitra)", "pawar.sachin@gmail.com", "Jalna", "Partur", "9766554433", "Partur Town, Near Sub-District Hospital, Jalna"));
        rescuerList.add(new Rescuer("Dnyaneshwar Gaikwad", "dnyaneshwar.g@gmail.com", "Jalna", "Jafrabad", "9890112233", "Jafrabad Central, Jalna"));
        rescuerList.add(new Rescuer("Mahesh Deshmukh (Sarpa Mitra)", "mahesh.d@gmail.com", "Jalna", "Mantha", "9404556677", "Mantha Bus Stand Road, Jalna"));
        rescuerList.add(new Rescuer("Santosh Chavan (Sarpa Mitra)", "santosh.chavan@gmail.com", "Jalna", "Ghansavangi", "9823445566", "Ghansavangi Market, Jalna"));
        rescuerList.add(new Rescuer("Prakash Solanke (Sarpa Mitra)", "prakash.solanke@gmail.com", "Jalna", "Ambad", "9822998877", "Wadigodri Phata, Ambad, Jalna"));

        // ========== CHHATRAPATI SAMBHAJINAGAR (AURANGABAD) ==========
        rescuerList.add(new Rescuer("Akash More (Aurangabad Wildlife SOS)", "akash.more@aurangabadwild.org", "Chhatrapati Sambhajinagar", "Aurangabad", "9822001122", "CIDCO N-4, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Baba Shaikh (Master Snake Rescuer)", "baba.shaikh.snakes@gmail.com", "Chhatrapati Sambhajinagar", "Aurangabad", "9822338899", "Shahganj, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Anil Asaram Shirse (Sarpmitra)", "anil.shirse@gmail.com", "Chhatrapati Sambhajinagar", "Aurangabad", "9422701122", "Waluj MIDC, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Pooja Kulkarni (Sarpa Mitra)", "pooja.rescue@gmail.com", "Chhatrapati Sambhajinagar", "Paithan", "9823112233", "Near Nath Mandir, Paithan"));
        rescuerList.add(new Rescuer("Kailash Patil (Sarpa Mitra)", "kailash.patil@gmail.com", "Chhatrapati Sambhajinagar", "Kannad", "9860123456", "Kannad Town, Chhatrapati Sambhajinagar"));

        // ========== BEED DISTRICT ==========
        rescuerList.add(new Rescuer("Balasaheb Shinde (District Sarpmitra)", "balasaheb.beed@gmail.com", "Beed", "Beed", "9422119988", "Nagar Road, Beed"));
        rescuerList.add(new Rescuer("Sunil Gavhane (Wildlife Rescue Beed)", "sunil.gavhane@gmail.com", "Beed", "Ambajogai", "9822443322", "Morewadi, Ambajogai, Beed"));
        rescuerList.add(new Rescuer("Ganesh Jagtap (Sarpa Mitra)", "ganesh.jagtap@gmail.com", "Beed", "Georai", "9765443322", "National Highway 52, Georai, Beed"));

        // ========== PARBHANI & NANDED ==========
        rescuerList.add(new Rescuer("Shrikant Joshi (Sarpa Mitra)", "shrikant.joshi@gmail.com", "Parbhani", "Parbhani", "9822556677", "Subhash Road, Parbhani"));
        rescuerList.add(new Rescuer("Dr. Nitin Kulkarni (Herpetologist)", "nitin.kulkarni.nanded@gmail.com", "Nanded", "Nanded", "9422176655", "VIP Road, Nanded"));
        rescuerList.add(new Rescuer("Maroti Maske (Sarpa Mitra)", "maroti.maske@gmail.com", "Nanded", "Loha", "9860445566", "Loha Bypass, Nanded"));

        // ========== PUNE, NASHIK & WESTERN MAHARASHTRA ==========
        rescuerList.add(new Rescuer("RESQ Wildlife Helpline Pune", "info@resqct.org", "Pune", "Haveli", "9822055110", "Bavdhan, Pune, Maharashtra 411021"));
        rescuerList.add(new Rescuer("Aftab Kalindar Shaikh (Katraj Sarpmitra)", "aftab.snakes@gmail.com", "Pune", "Haveli", "9822334455", "Katraj Snake Park Road, Pune"));
        rescuerList.add(new Rescuer("Vishal Bafna (Wild Animal Rescuers)", "vishal.bafna@nashikwild.org", "Nashik", "Nashik", "9850667788", "Panchavati, Nashik, Maharashtra 422003"));
        rescuerList.add(new Rescuer("Ankit Chafekar (Nature Club Nashik)", "ankit.snakes@gmail.com", "Nashik", "Nashik", "9890123450", "Gangapur Road, Nashik"));
        rescuerList.add(new Rescuer("Suhas Waingankar (Kolhapur Wildlife)", "suhas.kolhapur@gmail.com", "Kolhapur", "Karveer", "9422045566", "Rajarampuri, Kolhapur"));
        rescuerList.add(new Rescuer("Amol Jadhav (Solapur Sarpmitra)", "amol.jadhav@gmail.com", "Solapur", "Solapur", "9822119900", "Saat Rasta, Solapur"));
    }

    public static synchronized List<Rescuer> getRescuers() {
        return new ArrayList<>(rescuerList);
    }

    public static synchronized void addRescuer(Rescuer rescuer) {
        rescuerList.add(0, rescuer);
    }
}
