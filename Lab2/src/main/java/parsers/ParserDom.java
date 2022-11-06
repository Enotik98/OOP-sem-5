package parsers;


import models.Package;
import models.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static parsers.Tag.*;

public class ParserDom {
    DocumentBuilderFactory dbf;

    private static Document buildDocument(String src) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(src);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }

    public static ArrayList<Medicine> parse(String src) {
        Document document;
        ArrayList<Medicine> preparations = new ArrayList<>();
        try {
            document = buildDocument(src);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Node rootNode = document.getFirstChild();
        NodeList medicines = rootNode.getChildNodes();
        for (int i = 0; i < medicines.getLength(); i++) {
            Certificate certificate = null;
            Package pack =null;
            Dosage dosage = null;
            Medicine medicine = null;
            ArrayList<String> analogsArr = new ArrayList<>();
            ArrayList<Version> versionsArr = new ArrayList<>();

            if (medicines.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            NamedNodeMap medicineAttr = medicines.item(i).getAttributes();
            String name = medicineAttr.getNamedItem(MEDICINE_NAME.getTag()).getNodeValue();
            String pharm = medicineAttr.getNamedItem(MEDICINE_PHARM.getTag()).getNodeValue();
            String group = medicineAttr.getNamedItem(MEDICINE_GROUP.getTag()).getNodeValue();

            //Take Analogs and Versions
            NodeList medicine_elements = medicines.item(i).getChildNodes();
            for (int j = 0; j < medicine_elements.getLength(); j++) {
                if (medicine_elements.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                //check Analogs
                if (medicine_elements.item(j).getNodeName() == ANALOGS.getTag()) {
                    NodeList analogs = medicine_elements.item(j).getChildNodes();
                    for (int k = 0; k < analogs.getLength(); k++) {
                        if (analogs.item(k).getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                        analogsArr.add(analogs.item(k).getTextContent());
                    }
                }
                //check Versions
                if (medicine_elements.item(j).getNodeName() == VERSIONS.getTag()) {
                    NodeList versions = medicine_elements.item(j).getChildNodes();
                    for (int k = 0; k < versions.getLength(); k++) {
                        if (versions.item(k).getNodeType() != Node.ELEMENT_NODE) {
                            continue;
                        }
                        //Take Certificate, Package, Dosage
                        NodeList versions_element = versions.item(k).getChildNodes();
                        String type = versions.item(k).getAttributes().getNamedItem(VERSION_TYPE.getTag()).getNodeValue();
                        for (int l = 0; l < versions_element.getLength(); l++) {
                            if (versions_element.item(l).getNodeType() != Node.ELEMENT_NODE) {
                                continue;
                            }

                            if (versions_element.item(l).getNodeName() == CERTIFICATE.getTag()){
                                NamedNodeMap certificateAttr = versions_element.item(l).getAttributes();
                                int number = Integer.parseInt(certificateAttr.getNamedItem(CERTIFICATE_NUM.getTag()).getNodeValue());
                                String production_date = certificateAttr.getNamedItem(CERTIFICATE_PROD_DATE.getTag()).getNodeValue();
                                String exp_date = certificateAttr.getNamedItem(CERTIFICATE_EXP_DATE.getTag()).getNodeValue();
                                String reg_org = certificateAttr.getNamedItem(CERTIFICATE_REG_ORG.getTag()).getNodeValue();
                                certificate = new Certificate(number, production_date, exp_date, reg_org);
                            }
                            if (versions_element.item(l).getNodeName() == PACKAGE.getTag()){
                                NamedNodeMap packAttr = versions_element.item(l).getAttributes();
                                String pack_type = packAttr.getNamedItem(PACKAGE_TYPE.getTag()).getNodeValue();
                                int qty = Integer.parseInt(packAttr.getNamedItem(PACKAGE_QTY.getTag()).getNodeValue());
                                double price = Double.parseDouble(packAttr.getNamedItem(PACKAGE_PRICE.getTag()).getNodeValue());
                                pack = new Package(pack_type, qty, price);
                            }
                            if (versions_element.item(l).getNodeName() == DOSAGE.getTag()){
                                NamedNodeMap dosageAttr = versions_element.item(l).getAttributes();
                                int dose = Integer.parseInt(dosageAttr.getNamedItem(DOSAGE_DOSE.getTag()).getNodeValue());
                                double frq_hours = Double.parseDouble(dosageAttr.getNamedItem(DOSAGE_FRQ_HOURS.getTag()).getNodeValue());
                                dosage = new Dosage(dose, frq_hours);
                            }

                        }
                            Version version = new Version(type, certificate, pack, dosage);
                            versionsArr.add(version);
                    }
                }
            }

            medicine = new Medicine(name, pharm, group, analogsArr, versionsArr);
            preparations.add(medicine);
        }

        return preparations;
    }

}
