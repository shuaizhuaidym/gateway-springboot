package springbootvue.common;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import springbootvue.common.xml.XmlParser;

public class XMLParserTest {

	String initRandom() {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<message>");
		xml.append("<head>");
		xml.append("    <version>1.0</version>");
		xml.append("    <servicetype>OriginalService</servicetype>");
		xml.append("    <messageState>false</messageState>");
		xml.append("</head>");
		xml.append("<body>");
		xml.append("    <original>MjRkZmM1ODg3ZjQ4NGUwZGI0ODg5ZGYwYjc0MTUyZWM=</original>");
		xml.append("</body>");
		xml.append("</message>");
		return xml.toString();
	}

	String initResult() {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("<message>");
		xml.append("<head>");
		xml.append("    <version>1.0</version>");
		xml.append("    <serviceType>AuthenService</serviceType>");
		xml.append("    <messageState>false</messageState>");
		xml.append("</head>");
		xml.append("<body>");
		xml.append("    <accessControlResult>Permit</accessControlResult>");
		xml.append("    <authResultSet allFailed=\"true\">");
		xml.append("        <authResult authMode=\"cert\" success=\"true\"/>");
		xml.append("    </authResultSet>");
		xml.append("    <attributes>");
		xml.append(
				"        <attr name=\"X509Certificate.SubjectDN\" namespace=\"http://www.jit.com.cn/cinas/ias/ns/saml/saml11/X.509\">CN=yanming_dai,E=yanming_dai@jit.com.cn,O=JIT,C=CN</attr>");
		xml.append("    </attributes>");
		xml.append("    <token>cfe34e3d-81b8-4951-9515-9eddC0A80970</token>");
		xml.append("</body>");
		xml.append("</message>");
		return xml.toString();
	}

	@Test
	public void testParseRandom() throws ParserConfigurationException, SAXException, IOException {
		String x = new XmlParser().parseRandom(initRandom());
		Assert.assertEquals("random request error", x, "MjRkZmM1ODg3ZjQ4NGUwZGI0ODg5ZGYwYjc0MTUyZWM=");
		System.out.println(x);
	}

	@Test
	public void testParseAuthenticationResult() throws SAXException, IOException {
		AuthenticaitonResult x = new XmlParser().parseAuthenticationResult(this.initResult());
		Assert.assertEquals("Auth result is not success=true", "true", x.getSuccess());
		Assert.assertEquals("Subject not patch", "CN=yanming_dai,E=yanming_dai@jit.com.cn,O=JIT,C=CN",
				x.getAttributes().get("X509Certificate.SubjectDN"));

		System.out.println("success:" + x.getSuccess());
		System.out.println("X509Certificate.SubjectDN:" + x.getAttributes().get("X509Certificate.SubjectDN"));

	}
}
