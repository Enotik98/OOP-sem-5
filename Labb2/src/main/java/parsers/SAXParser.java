package parsers;

import org.xml.sax.SAXException;
import utils.XMLCreator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SAXParser extends ParserXML {

    public SAXParser(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String xmlPath){
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            javax.xml.parsers.SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xmlPath, drugHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(drugHandler.getMedicine().getDrugList(),"src/main/resources/sax.xml");
    }
}