package parsers;

import models.Medicine;
import org.w3c.dom.Document;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class ParserSAX {
    public static ArrayList<Medicine> parse(String src) {
        Document document;
        ArrayList<Medicine> preparations = new ArrayList<>();
        try {
            File file = new File(src);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParserHandler handler = new SAXParserHandler();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler);
            preparations = handler.getPreparations();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return preparations;
    }
}
