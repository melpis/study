

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author 홍성원
 *
 */
public class CreateSAXParser {

		private Map<String, String> servletMap=null;
	
	
		public CreateSAXParser(String path) {
			
			XMLHandler xmlHandler= new XMLHandler() ;
			SAXParserFactory factory = SAXParserFactory.newInstance();
			
			factory.setNamespaceAware(true);
			factory.setValidating(true);
			
			SAXParser saxParser=null;
			
			try {
				saxParser = factory.newSAXParser();
				saxParser.parse(path, xmlHandler);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.servletMap=xmlHandler.getServletMap();
			
		}


		public Map<String, String> getServletMap() {
			return servletMap;
		}

		



		
		
}
