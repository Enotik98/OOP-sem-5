

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import parsers.DOMParser;
import parsers.SAXParser;
import parsers.STAXParser;
import utils.XMLCreator;
import utils.XMLValidator;

import java.io.File;
import java.io.IOException;

public class ParsersTest {


    @Test
    public void testMain(){
        XMLCreator xmlCreator = new XMLCreator();
        DOMParser domParser = new DOMParser(xmlCreator);
        SAXParser saxParser = new SAXParser(xmlCreator);
        STAXParser staxParser = new STAXParser(xmlCreator);

        String XML = "src/main/resources/input.xml";
        String XSD = "src/main/resources/input.xsd";
        if(XMLValidator.validateXML(XML, XSD)){
            System.out.println("XML is valid");
        }
        else System.out.println("XML is not valid");
        saxParser.parse(XML);
        saxParser.createXML();
        staxParser.parse(XML);
        staxParser.createXML();
        domParser.parse(XML);
        domParser.createXML();
        try {
            Assert.assertTrue(FileUtils.contentEquals(new File("src/main/resources/dom.xml"),
                    new File("src/main/resources/sax.xml")));
            Assert.assertTrue(FileUtils.contentEquals(new File("src/main/resources/sax.xml"),
                    new File("src/main/resources/stax.xml")));
        }catch (IOException e){
            System.out.print(e);
        }
    }
}
