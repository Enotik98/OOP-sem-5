package parsers;

import utils.XMLCreator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class STAXParser extends ParserXML {

    public STAXParser(XMLCreator xmlCreator) {
        this.xmlCreator = xmlCreator;
    }

    @Override
    public void parse(String XMLFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader;
        try {
            reader = xmlInputFactory.createXMLEventReader(new FileInputStream(XMLFile));
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    String attributeString = null;
                    nextEvent = reader.nextEvent();
                    String string = startElement.getName().getLocalPart();
                    if (nextEvent.isCharacters()) {
                        drugHandler.setElementValue(nextEvent.asCharacters().getData());
                        drugHandler.setField(string, attributeString);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println(e);
        }
    }

    @Override
    public void createXML() {
        xmlCreator.buildXML(drugHandler.getMedicine().getDrugList(), "src/main/resources/stax.xml");
    }
}
