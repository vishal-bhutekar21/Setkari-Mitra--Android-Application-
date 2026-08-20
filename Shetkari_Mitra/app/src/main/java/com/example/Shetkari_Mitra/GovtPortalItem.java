package com.example.Shetkari_Mitra;

public class GovtPortalItem {
    private String title;
    private String marathiTitle;
    private String department;
    private String url;
    private String description;
    private String helpline;

    public GovtPortalItem(String title, String marathiTitle, String department, String url, String description, String helpline) {
        this.title = title;
        this.marathiTitle = marathiTitle;
        this.department = department;
        this.url = url;
        this.description = description;
        this.helpline = helpline;
    }

    public String getTitle() { return title; }
    public String getMarathiTitle() { return marathiTitle; }
    public String getDepartment() { return department; }
    public String getUrl() { return url; }
    public String getDescription() { return description; }
    public String getHelpline() { return helpline; }
}
