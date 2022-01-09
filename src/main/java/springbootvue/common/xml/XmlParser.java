package springbootvue.common.xml;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import springbootvue.common.AuthenticaitonResult;

public class XmlParser {

	/**
	 * parse random request result
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public String parseRandom(String xml) throws ParserConfigurationException, SAXException, IOException {
		SAXParser parser = createSaxParser();

		InputSource in = new InputSource(new StringReader(xml));

		RandomHandler handler = new RandomHandler();
		parser.parse(in, handler);

		return handler.readRandom();
	}

	/**
	 * parse authenticate result and attributes
	 * 
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public AuthenticaitonResult parseAuthenticationResult(String xml) throws SAXException, IOException {
		SAXParser parser = createSaxParser();

		InputSource in = new InputSource(new StringReader(xml));

		AuthenticationHandler handler = new AuthenticationHandler();
		parser.parse(in, handler);

		return handler.getResult();
	}

	/**
	 * parse and print xml content only
	 * 
	 * @param xml
	 */
	public void parseAndPrint(String xml) {
		SAXParser parser = createSaxParser();

		InputSource in = new InputSource(new StringReader(xml));

		PrintHandler handler = new PrintHandler();
		try {
			parser.parse(in, handler);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

	}

	private SAXParser createSaxParser() {
		SAXParser saxParser = null;

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			saxParser = factory.newSAXParser();
			return saxParser;
		} catch (ParserConfigurationException | SAXException ex) {
			Logger logger = Logger.getLogger(XmlParser.class.getName());
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
		return saxParser;

	}
}
