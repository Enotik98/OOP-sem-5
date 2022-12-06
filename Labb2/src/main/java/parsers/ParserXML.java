package parsers;

import utils.XMLCreator;

public abstract class ParserXML {
    protected XMLCreator xmlCreator;

    DrugHandler drugHandler = new DrugHandler();

    public abstract void parse(String XMLFile);

    public abstract void createXML();
}