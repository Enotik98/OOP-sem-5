import models.Package;
import models.*;
import org.junit.Assert;
import org.junit.Test;
import parsers.ParserStAX;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static Utils.Utils.sortByName;

public class ParserStAX_Test {
    private static final String src = "src/main/resources/Preparations.xml";

    @Test
    public void MedicineAttrTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = parserStAX.parse(src);
        Assert.assertEquals("boba", preparations.get(0).getName());
        Assert.assertEquals("boba-i-biba", preparations.get(0).getPharm());
        Assert.assertEquals("antibiotic", preparations.get(0).getGroup());

        Assert.assertEquals("amogus", preparations.get(1).getName());
        Assert.assertEquals("bab", preparations.get(1).getPharm());
        Assert.assertEquals("antibiotic", preparations.get(1).getGroup());

    }
    @Test
    public void MedicineAnalogsTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = parserStAX.parse(src);
        ArrayList<String> analogs0 = new ArrayList<>(Arrays.asList("boba", "biba"));
        ArrayList<String> analogs1 = new ArrayList<>(Arrays.asList("baba"));

        Assert.assertEquals(analogs0, preparations.get(0).getAnalogs());
        Assert.assertEquals(analogs1, preparations.get(1).getAnalogs());
    }
    @Test
    public void MedicineCertificateTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = parserStAX.parse(src);
        Certificate certificate00 = new Certificate(12, "01.01.2001", "01.01.2002", "farm1");
        Certificate certificate01 = new Certificate(111, "01.01.2001", "01.01.2002", "farm1");
        Certificate certificate10 = new Certificate(111, "01.01.2001", "01.01.2002", "farm2");

        Assert.assertEquals(certificate00, preparations.get(0).getVersions().get(0).getCertificate());
        Assert.assertEquals(certificate01, preparations.get(0).getVersions().get(1).getCertificate());
        Assert.assertEquals(certificate10, preparations.get(1).getVersions().get(0).getCertificate());
    }
    @Test
    public void MedicinePackageTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = parserStAX.parse(src);
        Package pack00 = new Package("box", 20, 100);
        Package pack01 = new Package("box", 25, 100);
        Package pack10 = new Package("box", 25, 100);

        Assert.assertEquals(pack00, preparations.get(0).getVersions().get(0).getPack());
        Assert.assertEquals(pack01, preparations.get(0).getVersions().get(1).getPack());
        Assert.assertEquals(pack10, preparations.get(1).getVersions().get(0).getPack());
    }
    @Test
    public void MedicineDosageTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = parserStAX.parse(src);
        Dosage dosage00 = new Dosage(2, 1);
        Dosage dosage01 = new Dosage(5, 1);
        Dosage dosage10 = new Dosage(5, 1);

        Assert.assertEquals(dosage00, preparations.get(0).getVersions().get(0).getDosage());
        Assert.assertEquals(dosage01, preparations.get(0).getVersions().get(1).getDosage());
        Assert.assertEquals(dosage10, preparations.get(1).getVersions().get(0).getDosage());
    }

    @Test
    public void MedicineVersionTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = parserStAX.parse(src);
        Version version00 = new Version("caps");
        Version version01 = new Version("box");
        Version version10 = new Version("box");

        Assert.assertEquals(version00.getType(), preparations.get(0).getVersions().get(0).getType());
        Assert.assertEquals(version01.getType(), preparations.get(0).getVersions().get(1).getType());
        Assert.assertEquals(version10.getType(), preparations.get(1).getVersions().get(0).getType());
    }
    @Test
    public void MedicineSortByNameTest() throws XMLStreamException, FileNotFoundException {
        ParserStAX parserStAX = new ParserStAX();
        ArrayList<Medicine> preparations = sortByName(parserStAX.parse(src));
        String positions1 = "amogus";
        String positions2 = "boba";

        Assert.assertEquals(positions1, preparations.get(0).getName());
        Assert.assertEquals(positions2, preparations.get(1).getName());
    }
}
