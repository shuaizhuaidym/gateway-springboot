package springbootvue.common.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RandomHandler extends DefaultHandler {
    private String value = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("original".equals(qName)) {
            value = "";
        }

    }

    @Override
    public void characters(char[] buffer, int start, int length) throws SAXException {
        value = new String(buffer, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ("original".equals(qName)) {
            // TODO
        }
    }

    public String readRandom() {
        return value;
    }

}
