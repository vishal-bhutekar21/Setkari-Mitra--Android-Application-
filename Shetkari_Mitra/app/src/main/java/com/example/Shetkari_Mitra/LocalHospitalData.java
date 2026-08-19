package com.example.Shetkari_Mitra;

import java.util.ArrayList;
import java.util.List;

/**
 * Local repository of hospitals in Jalna district, Maharashtra.
 * This replaces the previous Firebase-backed hospital database.
 * To add a real backend later, implement a HospitalRepository interface
 * and swap this data source.
 */
public class LocalHospitalData {

    public static List<Hospital_Info> getJalnaHospitals() {
        List<Hospital_Info> hospitals = new ArrayList<>();

        // ========== GOVERNMENT / PUBLIC HOSPITALS ==========

        hospitals.add(new Hospital_Info(
                "Government Medical College & Hospital (GMCH) Jalna",
                "Medical Superintendent",
                "02482-222000",
                "Jalna",
                "Jalna",
                "Jalna–Aurangabad Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "District Civil Hospital Jalna",
                "Civil Surgeon",
                "02482-222100",
                "Jalna",
                "Jalna",
                "Near District Collector Office, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Sub District Hospital Ambad",
                "Medical Officer",
                "02484-222200",
                "Ambad",
                "Jalna",
                "Main Road, Ambad, Jalna District, Maharashtra 431204"
        ));

        hospitals.add(new Hospital_Info(
                "Sub District Hospital Badnapur",
                "Medical Officer",
                "02481-234500",
                "Badnapur",
                "Jalna",
                "Hospital Road, Badnapur, Jalna District, Maharashtra 431202"
        ));

        hospitals.add(new Hospital_Info(
                "Sub District Hospital Partur",
                "Medical Officer",
                "02485-222300",
                "Partur",
                "Jalna",
                "Civil Lines, Partur, Jalna District, Maharashtra 431501"
        ));

        hospitals.add(new Hospital_Info(
                "Sub District Hospital Bhokardan",
                "Medical Officer",
                "02486-222100",
                "Bhokardan",
                "Jalna",
                "Near Bus Stand, Bhokardan, Jalna District, Maharashtra 431114"
        ));

        hospitals.add(new Hospital_Info(
                "Rural Hospital Jafrabad",
                "Medical Officer In-Charge",
                "02483-222400",
                "Jafrabad",
                "Jalna",
                "Hospital Colony, Jafrabad, Jalna District, Maharashtra 431206"
        ));

        hospitals.add(new Hospital_Info(
                "Rural Hospital Mantha",
                "Medical Officer In-Charge",
                "02487-222100",
                "Mantha",
                "Jalna",
                "Mantha, Jalna District, Maharashtra 431505"
        ));

        hospitals.add(new Hospital_Info(
                "Rural Hospital Ghansavangi",
                "Medical Officer In-Charge",
                "02482-265000",
                "Ghansavangi",
                "Jalna",
                "Ghansavangi, Jalna District, Maharashtra 431209"
        ));

        hospitals.add(new Hospital_Info(
                "Primary Health Centre Tirthpuri",
                "Medical Officer",
                "02482-260100",
                "Jalna",
                "Jalna",
                "Tirthpuri Village, Jalna Taluka, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Primary Health Centre Shirad",
                "Medical Officer",
                "02482-260200",
                "Jalna",
                "Jalna",
                "Shirad Village, Jalna Taluka, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Primary Health Centre Palshi",
                "Medical Officer",
                "02484-260300",
                "Ambad",
                "Jalna",
                "Palshi Village, Ambad Taluka, Jalna District, Maharashtra"
        ));

        hospitals.add(new Hospital_Info(
                "Rajmata Jijau Hospital Jalna",
                "Administrator",
                "02482-223000",
                "Jalna",
                "Jalna",
                "Near Railway Station, Jalna, Maharashtra 431203"
        ));

        // ========== PRIVATE HOSPITALS ==========

        hospitals.add(new Hospital_Info(
                "Sai Hospital Jalna",
                "Dr. Contact",
                "02482-224000",
                "Jalna",
                "Jalna",
                "Main Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Yashoda Hospital Jalna",
                "Reception",
                "02482-225000",
                "Jalna",
                "Jalna",
                "Station Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Ashirwad Hospital Jalna",
                "Reception",
                "02482-226000",
                "Jalna",
                "Jalna",
                "College Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Kranti Hospital Jalna",
                "Reception",
                "02482-227000",
                "Jalna",
                "Jalna",
                "Aurangabad Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Narayani Hospital Jalna",
                "Reception",
                "02482-228000",
                "Jalna",
                "Jalna",
                "Ambad Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Siddhi Vinayak Hospital Jalna",
                "Reception",
                "02482-229000",
                "Jalna",
                "Jalna",
                "Osmanabad Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Shri Sai Baba Hospital Jalna",
                "Reception",
                "02482-230000",
                "Jalna",
                "Jalna",
                "Nanded Road, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "People's Hospital Jalna",
                "Administration",
                "02482-231000",
                "Jalna",
                "Jalna",
                "Near Water Tank, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Shivshakti Hospital Jalna",
                "Reception",
                "02482-232000",
                "Jalna",
                "Jalna",
                "Shivaji Nagar, Jalna, Maharashtra 431203"
        ));

        hospitals.add(new Hospital_Info(
                "Matoshri Hospital Ambad",
                "Reception",
                "02484-222500",
                "Ambad",
                "Jalna",
                "Main Road, Ambad, Jalna District, Maharashtra 431204"
        ));

        hospitals.add(new Hospital_Info(
                "Dnyaneshwar Hospital Bhokardan",
                "Reception",
                "02486-222200",
                "Bhokardan",
                "Jalna",
                "Near Bus Stand, Bhokardan, Jalna District, Maharashtra 431114"
        ));

        hospitals.add(new Hospital_Info(
                "Seva Hospital Partur",
                "Reception",
                "02485-222400",
                "Partur",
                "Jalna",
                "Main Road, Partur, Jalna District, Maharashtra 431501"
        ));

        return hospitals;
    }

    // In-memory store for admin-submitted hospitals (runtime only)
    private static final List<Hospital_Info> adminAddedHospitals = new ArrayList<>();

    public static void addAdminHospital(Hospital_Info hospital) {
        adminAddedHospitals.add(hospital);
    }

    public static List<Hospital_Info> getAllHospitals() {
        List<Hospital_Info> all = new ArrayList<>();
        all.addAll(getJalnaHospitals());
        all.addAll(adminAddedHospitals);
        return all;
    }
}
