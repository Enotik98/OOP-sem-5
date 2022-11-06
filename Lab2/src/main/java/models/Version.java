package models;

public class Version {
    private String type;
    private Certificate certificate;
    private Package pack;
    private Dosage dosage;

    public Version(String type, Certificate certificate, Package pack, Dosage dosage) {
        this.type = type;
        this.certificate = certificate;
        this.pack = pack;
        this.dosage = dosage;
    }
    public Version(String type){
        this.type = type;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public String getType() {
        return type;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public Package getPack() {
        return pack;
    }

    public Dosage getDosage() {
        return dosage;
    }
}
