package parsers;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import models.Drug;
import models.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.XMLCreator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DOMParser extends ParserXML {

    public DOMParser() {}

    public DOMParser(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String xmlPath) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e);
        }

        Element root = doc.getDocumentElement();
        NodeList drugNodes = root.getElementsByTagName(drugHandler.getName());
        for (int i = 0; i < drugNodes.getLength(); i++) {
            Element drugElem = (Element) drugNodes.item(i);
            drugHandler.getMedicine().getDrugList().add(new Drug());
            drugHandler.lastestDrug().setVersion(new Version());
            NodeList childNodes = drugElem.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element child = (org.w3c.dom.Element) childNodes.item(j);
                    drugHandler.setElementValue(getChildValue(drugElem, child.getNodeName()));
                    drugHandler.setField(child.getNodeName());
                    NodeList childChildNodes = child.getChildNodes();
                    for (int k = 0; k < childChildNodes.getLength(); k++) {
                        if (childChildNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
                            Element childChild = (org.w3c.dom.Element) childChildNodes.item(k);
                            drugHandler.setElementValue(getChildValue(child, childChild.getNodeName()));
                            drugHandler.setField(childChild.getNodeName());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(drugHandler.getMedicine().getDrugList(),"src/main/resources/dom.xml");
    }

    protected static String getChildValue(Element element, String name) {
        Element child = (Element) element.getElementsByTagName(name).item(0);
        if (child == null) {
            return "";
        }
        Node node = child.getFirstChild();
        return node.getNodeValue();
    }
}