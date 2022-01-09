package springbootvue.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import springbootvue.common.xml.XmlParser;

public class RandomHelper {
	org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String random_template;

	public RandomHelper() {
		super();
		if (random_template == null)
			random_template = init();
	}

	public static RandomHelper instance() {
		return new RandomHelper();
	}

	String init() {
		StringBuilder template = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		template.append("<message>");
		template.append("    <head>");
		template.append("        <version>1.0</version>");
		template.append("        <serviceType>OriginalService</serviceType>");
		template.append("    </head>");
		template.append("    <body>");
		template.append("         <appId>%s</appId>");
		template.append("    </body>");
		template.append("</message>");
		return template.toString();
	}

	public String randomLocal(int length) {

		String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
		char[] charArray = num.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
		}
		return sb.toString();

	}

	public String randomRemote(String url, String appID)
			throws UnsupportedEncodingException, HttpException, IOException {
		String random = null;

		String requestXML = String.format(random_template, appID);

		int statusCode = 500;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);

		postMethod.setRequestHeader("Connection", "close");

		try {
			postMethod.setRequestEntity(new StringRequestEntity(requestXML, "text/xml", "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			throw e1;
		}
		try {
			statusCode = httpClient.executeMethod(postMethod);
		} catch (Exception e) {
			postMethod.releaseConnection();
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
			httpClient = null;
			e.printStackTrace();
			throw e;
		}

		String responseXml = null;
		if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
			try {
				responseXml = postMethod.getResponseBodyAsString();
				// 200 表示返回处理成功
				if (statusCode == HttpStatus.SC_OK) {
					// FIXME process XML head error code and error message
					random = new XmlParser().parseRandom(responseXml);
				} else {
					throw new RuntimeException("Gateway response error when request random");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} finally {
				if (httpClient != null) {
					postMethod.releaseConnection();
					httpClient.getHttpConnectionManager().closeIdleConnections(0);
				}
			}
		}
		return random;

	}

}
