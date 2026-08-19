package com.example.Shetkari_Mitra;

public class OnboardingItem {
    private final String title;
    private final String marathiTitle;
    private final String description;
    private final String badge;
    private final int imageRes;

    public OnboardingItem(String title, String marathiTitle, String description, String badge, int imageRes) {
        this.title = title;
        this.marathiTitle = marathiTitle;
        this.description = description;
        this.badge = badge;
        this.imageRes = imageRes;
    }

    public String getTitle() { return title; }
    public String getMarathiTitle() { return marathiTitle; }
    public String getDescription() { return description; }
    public String getBadge() { return badge; }
    public int getImageRes() { return imageRes; }
}
