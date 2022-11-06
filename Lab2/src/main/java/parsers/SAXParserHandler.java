package parsers;

import models.Package;
import models.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import static parsers.Tag.*;

public class SAXParserHandler extends DefaultHandler {
    private String currentTagName;
    private ArrayList<Medicine> preparations = null;
    private Medicine medicine = null;
    private ArrayList<String> analogs = null;
    private ArrayList<Version> versionArray = null;
    private Version version = null;
    private Certificate certificate = null;
    private Package pack = null;
    private Dosage dosage = null;

    @Override
    public void startDocument() throws SAXException {
        preparations = new ArrayList<>();

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(MEDICINE.getTag())){
            medicine = new Medicine();
            medicine.setName(attributes.getValue(MEDICINE_NAME.getTag()));
            medicine.setPharm(attributes.getValue(MEDICINE_PHARM.getTag()));
            medicine.setGroup(attributes.getValue(MEDICINE_GROUP.getTag()));
        } else if (qName.equalsIgnoreCase(ANALOGS.getTag())){
            analogs = new ArrayList<>();
        }else if (qName.equalsIgnoreCase(VERSIONS.getTag())){
            versionArray = new ArrayList<>();
        }else if (qName.equalsIgnoreCase(VERSION.getTag())){
            version = new Version(attributes.getValue(VERSION_TYPE.getTag()));
        }else if (qName.equalsIgnoreCase(CERTIFICATE.getTag())){
            int number = Integer.parseInt(attributes.getValue(CERTIFICATE_NUM.getTag()));
            String prod_date = attributes.getValue(CERTIFICATE_PROD_DATE.getTag());
            String exp_date = attributes.getValue(CERTIFICATE_EXP_DATE.getTag());
            String reg_org = attributes.getValue(CERTIFICATE_REG_ORG.getTag());
            certificate = new Certificate(number, prod_date, exp_date, reg_org);
        }else if (qName.equalsIgnoreCase(PACKAGE.getTag())){
            String type = attributes.getValue(PACKAGE_TYPE.getTag());
            int qty = Integer.parseInt(attributes.getValue(PACKAGE_QTY.getTag()));
            double price = Double.parseDouble(attributes.getValue(PACKAGE_PRICE.getTag()));
            pack = new Package(type, qty, price);
        }else if (qName.equalsIgnoreCase(DOSAGE.getTag())){
            int dose = Integer.parseInt(attributes.getValue(DOSAGE_DOSE.getTag()));
            double frq_hours = Double.parseDouble(attributes.getValue(DOSAGE_FRQ_HOURS.getTag()));
            dosage = new Dosage(dose, frq_hours);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(ANALOG.getTag())){
            String analog = currentTagName;
            analogs.add(analog);
        }else if (qName.equalsIgnoreCase(VERSION.getTag())){
            version.setCertificate(certificate);
            version.setDosage(dosage);
            version.setPack(pack);
            versionArray.add(version);
        }else if (qName.equalsIgnoreCase(VERSIONS.getTag())){
            medicine.setVersions(versionArray);
        }else if (qName.equalsIgnoreCase(ANALOGS.getTag())){
            medicine.setAnalogs(analogs);
        }else if (qName.equalsIgnoreCase(MEDICINE.getTag())){
            preparations.add(medicine);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentTagName = new String(ch, start, length);
    }

    public ArrayList<Medicine> getPreparations() {
        return preparations;
    }
}
