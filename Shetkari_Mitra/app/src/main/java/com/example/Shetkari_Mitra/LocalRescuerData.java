package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

/**
 * Local repository of verified snake rescuers in Maharashtra (focusing on Jalna & surrounding districts).
 * Replaces Firebase Realtime Database rescuers collection.
 */
public class LocalRescuerData {

    private static final List<Rescuer> rescuerList = new ArrayList<>();

    static {
        // Jalna District Rescuers
        rescuerList.add(new Rescuer("Rahul Shinde (Sarpa Mitra)", "rahul.rescuer@gmail.com", "Jalna", "Jalna", "9822114455", "Old Jalna, Near Railway Station, Jalna"));
        rescuerList.add(new Rescuer("Vikas Rathod", "vikas.snakes@gmail.com", "Jalna", "Ambad", "9422336677", "Ambad City, Near Bus Stand, Jalna"));
        rescuerList.add(new Rescuer("Anil Jadhav", "anil.wildlife@gmail.com", "Jalna", "Badnapur", "9977884411", "Station Road, Badnapur, Jalna"));
        rescuerList.add(new Rescuer("Gajanan Kale", "gajanan.rescuer@gmail.com", "Jalna", "Bhokardan", "9850123456", "Main Market, Bhokardan, Jalna"));
        rescuerList.add(new Rescuer("Sachin Pawar", "pawar.sachin@gmail.com", "Jalna", "Partur", "9766554433", "Partur Town, Near Sub-District Hospital, Jalna"));
        rescuerList.add(new Rescuer("Dnyaneshwar Gaikwad", "dnyaneshwar.g@gmail.com", "Jalna", "Jafrabad", "9890112233", "Jafrabad Central, Jalna"));
        rescuerList.add(new Rescuer("Mahesh Deshmukh", "mahesh.d@gmail.com", "Jalna", "Mantha", "9404556677", "Mantha Bus Stand Road, Jalna"));
        rescuerList.add(new Rescuer("Santosh Chavan", "santosh.chavan@gmail.com", "Jalna", "Ghansavangi", "9823445566", "Ghansavangi Market, Jalna"));

        // Chhatrapati Sambhajinagar (Aurangabad)
        rescuerList.add(new Rescuer("Akash More (Wildlife NGO)", "akash.more@aurangabadwild.org", "Chhatrapati Sambhajinagar", "Aurangabad", "9822001122", "CIDCO N-4, Chhatrapati Sambhajinagar"));
        rescuerList.add(new Rescuer("Pooja Kulkarni", "pooja.rescue@gmail.com", "Chhatrapati Sambhajinagar", "Paithan", "9823112233", "Near Nath Mandir, Paithan"));

        // Beed
        rescuerList.add(new Rescuer("Balasaheb Shinde", "balasaheb.beed@gmail.com", "Beed", "Beed", "9422119988", "Nagar Road, Beed"));

        // Pune
        rescuerList.add(new Rescuer("Swapnil Kumbhar", "swapnil.rescue@punesnakes.org", "Pune", "Haveli", "9822334455", "Kothrud, Pune"));

        // Nashik
        rescuerList.add(new Rescuer("Sanjay Bhamre", "bhamre.sanjay@gmail.com", "Nashik", "Nashik", "9850667788", "Panchavati, Nashik"));
    }

    public static synchronized List<Rescuer> getRescuers() {
        return new ArrayList<>(rescuerList);
    }

    public static synchronized void addRescuer(Rescuer rescuer) {
        rescuerList.add(0, rescuer);
    }
}
