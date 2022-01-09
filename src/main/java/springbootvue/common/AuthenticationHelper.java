package springbootvue.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.xml.sax.SAXException;

import springbootvue.common.xml.RequestBuilder;
import springbootvue.common.xml.XmlParser;

public class AuthenticationHelper {

	private static String authentication_template;

	public AuthenticationHelper() {
		super();
		if (authentication_template == null)
			authentication_template = init();
	}

	public static AuthenticationHelper instance() {
		return new AuthenticationHelper();
	}

	String init() {
		StringBuilder template = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		template.append("<message>");
		template.append("<head>");
		template.append("    <version>1.1</version>");
		template.append("    <serviceType>AuthenService</serviceType>");
		template.append("</head>");
		template.append("<body>");
		template.append("    <clientInfo>");
		template.append("        <clientIP>172.16.7.80</clientIP>");
		template.append("    </clientInfo>");
		template.append("    <appId>testApp</appId>");
		template.append("    <authen>");
		template.append("        <authCredential authMode=\"cert\">");
		template.append("            <detach>%s</detach>");
		template.append("            <original>%s</original>");
		template.append("        </authCredential>");
		template.append("    </authen>");
		template.append("    <accessControl>false</accessControl>");
		template.append("    <attributes attributeType=\"all\"/>");
		template.append("</body>");
		template.append("</message>");

		return template.toString();

	}

	public AuthenticaitonResult authenticate(String url, GatewayConfigurationProp configure,
			Map<String, String> request) {
		AuthenticaitonResult result = null;

		String requestXML = RequestBuilder.instance().buildRequest(configure, request);

		int statusCode = 500;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);

		postMethod.setRequestHeader("Connection", "close");

		try {
			postMethod.setRequestEntity(new StringRequestEntity(requestXML, "text/xml", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			// TODO
		}
		try {
			statusCode = httpClient.executeMethod(postMethod);
		} catch (Exception e) {
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
			httpClient = null;
			e.printStackTrace();
			return null;
		}

		String responseXml = null;
		if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
			try {
				responseXml = postMethod.getResponseBodyAsString();
				// 200 表示返回处理成功
				if (statusCode == HttpStatus.SC_OK) {
					result = new XmlParser().parseAuthenticationResult(responseXml);
				} else {
					throw new RuntimeException("Gateway response error code"+statusCode+" when request authenticating");
				}
			} catch (IOException | SAXException e) {
				throw new RuntimeException("Parse response xml by SAX found ERROR", e);
			} finally {
				if (httpClient != null) {
					postMethod.releaseConnection();
					httpClient.getHttpConnectionManager().closeIdleConnections(0);
				}
			}
		}
		return result;

	}

}
