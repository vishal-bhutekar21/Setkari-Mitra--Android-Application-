package com.example.Shetkari_Mitra;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MaharashtraDistrictHelper {

    public static ArrayAdapter<CharSequence> getTalukaAdapter(Context context, int districtPosition) {
        ArrayAdapter<CharSequence> adapter;

        switch (districtPosition) {
            case 0:
                adapter = ArrayAdapter.createFromResource(context, R.array.ahmednagar_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 1:
                adapter = ArrayAdapter.createFromResource(context, R.array.akola_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 2:
                adapter = ArrayAdapter.createFromResource(context, R.array.amravati_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 3:
                adapter = ArrayAdapter.createFromResource(context, R.array.sambhajinagar_taluka_array, android.R.layout.simple_spinner_item);
                break;
            case 4:
                adapter = ArrayAdapter.createFromResource(context, R.array.beed_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 5:
                adapter = ArrayAdapter.createFromResource(context, R.array.bhandara_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 6:
                adapter = ArrayAdapter.createFromResource(context, R.array.buldhana_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 7:
                adapter = ArrayAdapter.createFromResource(context, R.array.chandrapur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 8:
                adapter = ArrayAdapter.createFromResource(context, R.array.dhule_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 9:
                adapter = ArrayAdapter.createFromResource(context, R.array.gadchiroli_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 10:
                adapter = ArrayAdapter.createFromResource(context, R.array.gondiya_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 11:
                adapter = ArrayAdapter.createFromResource(context, R.array.hingoli_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 12:
                adapter = ArrayAdapter.createFromResource(context, R.array.jalgaon_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 13:
                adapter = ArrayAdapter.createFromResource(context, R.array.jalna_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 14:
                adapter = ArrayAdapter.createFromResource(context, R.array.kolhapur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 15:
                adapter = ArrayAdapter.createFromResource(context, R.array.latur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 16:
            case 17:
                adapter = ArrayAdapter.createFromResource(context, R.array.mumbai_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 18:
                adapter = ArrayAdapter.createFromResource(context, R.array.nagpur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 19:
                adapter = ArrayAdapter.createFromResource(context, R.array.nanded_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 20:
                adapter = ArrayAdapter.createFromResource(context, R.array.nandurbar_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 21:
                adapter = ArrayAdapter.createFromResource(context, R.array.nashik_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 22:
                adapter = ArrayAdapter.createFromResource(context, R.array.osmanabad_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 23:
                adapter = ArrayAdapter.createFromResource(context, R.array.palghar_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 24:
                adapter = ArrayAdapter.createFromResource(context, R.array.parbhani_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 25:
                adapter = ArrayAdapter.createFromResource(context, R.array.pune_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 26:
                adapter = ArrayAdapter.createFromResource(context, R.array.raigad_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 27:
                adapter = ArrayAdapter.createFromResource(context, R.array.ratnagiri_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 28:
                adapter = ArrayAdapter.createFromResource(context, R.array.sangli_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 29:
                adapter = ArrayAdapter.createFromResource(context, R.array.satara_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 30:
                adapter = ArrayAdapter.createFromResource(context, R.array.sindhudurg_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 31:
                adapter = ArrayAdapter.createFromResource(context, R.array.solapur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 32:
                adapter = ArrayAdapter.createFromResource(context, R.array.thane_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 33:
                adapter = ArrayAdapter.createFromResource(context, R.array.wardha_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 34:
                adapter = ArrayAdapter.createFromResource(context, R.array.washim_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 35:
                adapter = ArrayAdapter.createFromResource(context, R.array.yavatmal_talukas_array, android.R.layout.simple_spinner_item);
                break;
            default:
                adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
                break;
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
