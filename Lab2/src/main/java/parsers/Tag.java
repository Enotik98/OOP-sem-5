package parsers;

public enum Tag {
    PREPARATIONS("Preparations"),
    MEDICINE("Medicine"),
    MEDICINE_NAME("name"),
    MEDICINE_PHARM("pharm"),
    MEDICINE_GROUP("group"),
    ANALOGS("Analogs"),
    ANALOG("Analog"),
    VERSIONS("Versions"),
    VERSION("Version"),
    VERSION_TYPE("type"),
    CERTIFICATE("Certificate"),
    CERTIFICATE_NUM("num"),
    CERTIFICATE_PROD_DATE("production_date"),
    CERTIFICATE_EXP_DATE("exp_date"),
    CERTIFICATE_REG_ORG("reg_org"),
    PACKAGE("Package"),
    PACKAGE_TYPE("type"),
    PACKAGE_QTY("qty"),
    PACKAGE_PRICE("price"),
    DOSAGE("Dosage"),
    DOSAGE_DOSE("dose"),
    DOSAGE_FRQ_HOURS("frq_hours"),
    ;
    private String tag;

    Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }


    public String getTag() {
        return tag;
    }
}
