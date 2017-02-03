

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

    private List<String> servletList = null;
    private String currentElement = "";
    private Map<String, String> servletMap = null;


    public Map<String, String> getServletMap() {
        return servletMap;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        this.currentElement = qName;
        if ("webapp".equals(qName)) {

            servletMap = new HashMap<>();

        }
        if ("servlet".equals(qName)) {
            servletList = new ArrayList<>();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String value = new String(ch, start, length);

        if ("url".equals(this.currentElement) || "servlet-class".equals(this.currentElement)) {
            servletList.add(value);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        this.currentElement = "";
        if ("servlet".equals(qName)) {
            this.servletMap.put(this.servletList.get(0), this.servletList.get(1));
            this.servletList.removeAll(servletList);
        }
    }


}
