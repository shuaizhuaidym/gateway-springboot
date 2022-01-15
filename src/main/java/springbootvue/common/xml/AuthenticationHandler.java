package springbootvue.common.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import springbootvue.common.Attribute;
import springbootvue.common.AuthenticaitonResult;
/**
 * @author daiyma
 * authentication result parser handler
 */
public class AuthenticationHandler extends DefaultHandler {

	private AuthenticaitonResult result = new AuthenticaitonResult();

	private List<springbootvue.common.Attribute> attrs = new ArrayList<>();

//	private String name = "";

	private String value = "";
	
	private springbootvue.common.Attribute attr;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("message".equals(qName)) {
			result = new AuthenticaitonResult();
		}
		if ("authResult".equals(qName)) {
			result.setAuthMode(attributes.getValue("authMode"));
			result.setSuccess(attributes.getValue("success"));
		}
		if ("attributes".equals(qName)) {
			attrs = new ArrayList<>();
		}
		if ("attr".equals(qName)) {
			attr = new Attribute(attributes.getValue("name"),attributes.getValue("namespace"));
			attrs.add(attr);
		}
		if ("token".equals(qName)) {
//			name = qName;
		}
		if ("messageState".equals(qName)) {
//			name = qName;
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
			result.setSuccess("false");
		}
		if ("messageDesc".equals(qName)) {
			result.setMessageDesc(value);
		}
		
		if ("accessControlResult".equals(qName)) {
			result.setAccessControlResult(value);
		}

		if ("attr".equals(qName)) {
			attr.setValue(value);
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
