
import parsers.DOMParser;
import parsers.SAXParser;
import parsers.STAXParser;
import utils.XMLCreator;
import utils.XMLValidator;

import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        XMLCreator xmlCreator = new XMLCreator();
        DOMParser domParser = new DOMParser(xmlCreator);
        SAXParser saxParser = new SAXParser(xmlCreator);
        STAXParser staxParser = new STAXParser(xmlCreator);

        String XML = "src/main/resources/input.xml";
        String XSD = "src/main/resources/input.xsd";
        if(XMLValidator.validateXML(XML, XSD)){
            System.out.println("XML is valid");
        }
        else {
            System.out.println("XML is not valid");
        }
        saxParser.parse(XML);
        saxParser.createXML();
        staxParser.parse(XML);
        staxParser.createXML();
        domParser.parse(XML);
        domParser.createXML();
    }
}