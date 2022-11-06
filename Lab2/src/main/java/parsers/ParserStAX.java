package parsers;

import models.Package;
import models.*;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static parsers.Tag.*;

public class ParserStAX {
    public static ArrayList<Medicine> parse(String src) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        File file = new File(src);
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
        ArrayList<Medicine> preparations = new ArrayList<>();
        Medicine medicine = null;
        ArrayList<String> analogs = null;
        ArrayList<Version> versionArray = null;
        Version version = null;
        Certificate certificate = null;
        Package pack = null;
        Dosage dosage = null;
        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals(MEDICINE.getTag())) {
                    medicine = new Medicine();
                    Attribute name = startElement.getAttributeByName(new QName(MEDICINE_NAME.getTag()));
                    Attribute pharm = startElement.getAttributeByName(new QName(MEDICINE_PHARM.getTag()));
                    Attribute group = startElement.getAttributeByName(new QName(MEDICINE_GROUP.getTag()));
                    if (name != null) medicine.setName(name.getValue());
                    if (pharm != null) medicine.setPharm(pharm.getValue());
                    if (group != null) medicine.setGroup(group.getValue());
                } else if (startElement.getName().getLocalPart().equals(ANALOGS.getTag())) {
                    analogs = new ArrayList<>();
                } else if (startElement.getName().getLocalPart().equals(ANALOG.getTag())){
                    nextEvent = reader.nextEvent();
                    analogs.add(nextEvent.asCharacters().getData());
                } else if (startElement.getName().getLocalPart().equals(VERSIONS.getTag())){
                    versionArray = new ArrayList<>();
                } else if (startElement.getName().getLocalPart().equals(VERSION.getTag())){
                    Attribute type = startElement.getAttributeByName(new QName(VERSION_TYPE.getTag()));
                    if (type != null) version = new Version(type.getValue());
                } else if (startElement.getName().getLocalPart().equals(CERTIFICATE.getTag())){
                    Attribute number = startElement.getAttributeByName(new QName(CERTIFICATE_NUM.getTag()));
                    Attribute prod_date = startElement.getAttributeByName(new QName(CERTIFICATE_PROD_DATE.getTag()));
                    Attribute exp_date = startElement.getAttributeByName(new QName(CERTIFICATE_EXP_DATE.getTag()));
                    Attribute reg_org = startElement.getAttributeByName(new QName(CERTIFICATE_REG_ORG.getTag()));
                    if (number != null && prod_date != null && exp_date != null && reg_org != null){
                        certificate = new Certificate(Integer.parseInt(number.getValue()), prod_date.getValue(), exp_date.getValue(), reg_org.getValue());
                    }
                }else if (startElement.getName().getLocalPart().equals(PACKAGE.getTag())){
                    Attribute type = startElement.getAttributeByName(new QName(PACKAGE_TYPE.getTag()));
                    Attribute qty = startElement.getAttributeByName(new QName(PACKAGE_QTY.getTag()));
                    Attribute price = startElement.getAttributeByName(new QName(PACKAGE_PRICE.getTag()));
                    if (type != null && qty != null && price != null){
                        pack = new Package(type.getValue(), Integer.parseInt(qty.getValue()), Double.parseDouble(price.getValue()));
                    }
                }else if (startElement.getName().getLocalPart().equals(DOSAGE.getTag())){
                    Attribute dose = startElement.getAttributeByName(new QName(DOSAGE_DOSE.getTag()));
                    Attribute frq_hours = startElement.getAttributeByName(new QName(DOSAGE_FRQ_HOURS.getTag()));
                    if (dose != null && frq_hours != null){
                        dosage = new Dosage(Integer.parseInt(dose.getValue()), Double.parseDouble(frq_hours.getValue()));
                    }
                }
            }
            if (nextEvent.isEndElement()){
                EndElement endElement = nextEvent.asEndElement();
                if (endElement.getName().getLocalPart().equals(MEDICINE.getTag())){
                    medicine.setAnalogs(analogs);
                    medicine.setVersions(versionArray);
                    preparations.add(medicine);
                }else if (endElement.getName().getLocalPart().equals(VERSION.getTag())){
                    version.setPack(pack);
                    version.setDosage(dosage);
                    version.setCertificate(certificate);
                    versionArray.add(version);
                }
            }
        }

        return preparations;
    }
}
