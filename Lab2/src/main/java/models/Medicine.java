package models;

import java.util.ArrayList;


public class Medicine {
    private String name;
    private String pharm;
    private String group;
    private ArrayList<String> analogs = new ArrayList<>();
    private ArrayList<Version> versions = new ArrayList<>();

    public Medicine(){}
    public Medicine(String name, String pharm, String group, ArrayList<String> analogs, ArrayList<Version> versions) {
        this.name = name;
        this.pharm = pharm;
        this.group = group;
        this.analogs = analogs;
        this.versions = versions;
    }


    public int compare(Medicine anotherMedicine){
        return this.name.compareTo(anotherMedicine.name);
    }

    public String getName() {
        return name;
    }

    public String getPharm() {
        return pharm;
    }

    public String getGroup() {
        return group;
    }

    public ArrayList<String> getAnalogs() {
        return analogs;
    }

    public ArrayList<Version> getVersions() {
        return versions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setAnalogs(ArrayList<String> analogs) {
        this.analogs = analogs;
    }

    public void setVersions(ArrayList<Version> versions) {
        this.versions = versions;
    }
}
