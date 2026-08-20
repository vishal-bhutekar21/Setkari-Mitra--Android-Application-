package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Locale;

public class VoiceAssistantHelper {

    public enum VoiceIntent {
        EMERGENCY_BITE,
        FIND_HOSPITAL,
        FIND_RESCUER,
        FIRST_AID_GUIDANCE,
        IDENTIFY_SNAKE,
        SAFETY_CHECKLIST,
        UNKNOWN
    }

    public static VoiceIntent parseSpokenQuery(String query) {
        if (query == null) return VoiceIntent.UNKNOWN;
        String normalized = query.toLowerCase(Locale.ROOT).trim();

        if (normalized.contains("bitten") || normalized.contains("bite") || normalized.contains("चावला") || normalized.contains("काटा") || normalized.contains("emergency") || normalized.contains("मदत")) {
            return VoiceIntent.EMERGENCY_BITE;
        } else if (normalized.contains("hospital") || normalized.contains("रुग्णालय") || normalized.contains("अस्पताल") || normalized.contains("doctor") || normalized.contains("antivenom")) {
            return VoiceIntent.FIND_HOSPITAL;
        } else if (normalized.contains("rescuer") || normalized.contains("sarpamitra") || normalized.contains("sarpmitra") || normalized.contains("सर्पमित्र") || normalized.contains("साप पकडणारा")) {
            return VoiceIntent.FIND_RESCUER;
        } else if (normalized.contains("identify") || normalized.contains("photo") || normalized.contains("camera") || normalized.contains("ओळख") || normalized.contains("पहचान")) {
            return VoiceIntent.IDENTIFY_SNAKE;
        } else if (normalized.contains("first aid") || normalized.contains("do") || normalized.contains("what should i do") || normalized.contains("प्रथमोपचार") || normalized.contains("उपचार")) {
            return VoiceIntent.FIRST_AID_GUIDANCE;
        } else if (normalized.contains("safety") || normalized.contains("checklist") || normalized.contains("सुरक्षा") || normalized.contains("नियम")) {
            return VoiceIntent.SAFETY_CHECKLIST;
        }

        return VoiceIntent.UNKNOWN;
    }

    public static void executeVoiceIntent(Context context, VoiceIntent intent) {
        if (context == null) return;

        switch (intent) {
            case EMERGENCY_BITE:
                context.startActivity(new Intent(context, EmergencyActivity.class));
                break;
            case FIND_HOSPITAL:
                context.startActivity(new Intent(context, Near_By_Hospitals.class));
                break;
            case FIND_RESCUER:
                context.startActivity(new Intent(context, RescuerDatabaseActivity.class));
                break;
            case IDENTIFY_SNAKE:
                context.startActivity(new Intent(context, Acitivity_identify_snake.class));
                break;
            case FIRST_AID_GUIDANCE:
                context.startActivity(new Intent(context, First_Aid.class));
                break;
            case SAFETY_CHECKLIST:
                context.startActivity(new Intent(context, SafetyCenterActivity.class));
                break;
            case UNKNOWN:
            default:
                Toast.makeText(context, "Voice command received. Opening Home Safety Dashboard.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
