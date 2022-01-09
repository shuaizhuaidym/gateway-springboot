package springbootvue.common.xml;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import springbootvue.common.AuthenticaitonResult;
/**
 * @author daiyma
 * authentication result parser handler
 */
public class AuthenticationHandler extends DefaultHandler {

	private AuthenticaitonResult result = new AuthenticaitonResult();

	private Map<String, String> attrs = new HashMap<>();

	private String name = "";

	private String value = "";

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("message".equals(qName)) {
			result = new AuthenticaitonResult();
		}
		// FIXME error when auth failed
		if ("authResult".equals(qName)) {
			result.setAuthMode(attributes.getValue("authMode"));
			result.setSuccess(attributes.getValue("success"));
		}
		if ("attributes".equals(qName)) {
			attrs = new HashMap<>();
		}
		if ("attr".equals(qName)) {
			name = attributes.getValue("name");
		}
		if ("token".equals(qName)) {
			name = qName;
		}
		if ("messageState".equals(qName)) {
			name = qName;
		}

	}

	@Override
	public void characters(char[] buffer, int start, int length) throws SAXException {
		value = new String(buffer, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// 成功:false/失败:true
		if ("messageState".equals(qName)) {
			result.setMessageState(value);
		}
		
		if ("messageCode".equals(qName)) {
			result.setMessageCode(value);
			//TODO 错误场景1
			result.setSuccess("false");
		}
		if ("messageDesc".equals(qName)) {
			result.setMessageDesc(value);
		}
		
		if ("accessControlResult".equals(qName)) {
			result.setAccessControlResult(value);
		}

		if ("attr".equals(qName)) {
			attrs.put(name, value);
		}
		if ("attributes".equals(qName)) {
			result.setAttributes(attrs);
		}
		if ("token".equals(qName)) {
			result.setToken(value);
		}
	}

	public String readRandom() {
		return value;
	}

	public AuthenticaitonResult getResult() {
		return result;
	}

}
