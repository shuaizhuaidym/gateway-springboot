package springbootvue.common.xml;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import springbootvue.common.GatewayConfigurationProp;
/**
 * <?xml version=\"1.0\" encoding=\"UTF-8\"?>
<message>
	<head>
		<version>1.1</version>
		<serviceType>AuthenService</serviceType>
	</head>
	<body>
		<clientInfo>
			<clientIP>172.16.7.80</clientIP>
		</clientInfo>
		<appId>testApp</appId>
		<authen>
			<authCredential authMode=\"cert\">
				<detach>MIID0AYJKoZIhvcNAQcCoIIDwTCCA70CAQExCzAJBgUrDgMCGgUAMAsGCSqGSIb3DQEHAaCCAqMwggKf=</detach>
				<original>TWpSa1ptTTFPRGczWmpRNE5HVXdaR0kwT0RnNVpHWXdZamMwTVRVeVpXTT0=</original>
			</authCredential>
		</authen>
		<accessControl>false</accessControl>
		<attributes attributeType=\"all\"/>
	</body>
</message>HTTP/1.0 200 OK
 * @author daiyma
 *
 */
public class RequestBuilder {
	
	public static RequestBuilder instance(){
		return new RequestBuilder();
	}
	public String buildRequest(GatewayConfigurationProp configure, Map<String, String> request) {

		String xml = "";
		// writes the users
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		XMLStreamWriter xsw = null;
		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			xsw = xof.createXMLStreamWriter(baos);

			xsw.writeStartDocument();

			xsw.writeStartElement("message");

			xsw.writeStartElement("head");
			
			this.wrap(xsw, "version", configure.getMessage_version());
			this.wrap(xsw, "serviceType", "AuthenService");
			
			xsw.writeEndElement(/* head */);

			xsw.writeStartElement("body");
			xsw.writeStartElement("clientInfo");

			this.wrap(xsw, "clientIP", request.get("clientIP"));

			xsw.writeEndElement(/* clientInfo */);

			this.wrap(xsw, "appId", configure.getAppID());

			xsw.writeStartElement("authen");
			xsw.writeStartElement("authCredential");
			xsw.writeAttribute("authMode", request.get("authMode"));

			this.wrap(xsw, "detach", request.get("detach"));
			this.wrap(xsw, "original", request.get("original"));

			xsw.writeEndElement(/* authCredential */);
			xsw.writeEndElement(/* authen */);

			this.wrap(xsw, "accessControl", configure.getAccess_control());
			
			xsw.writeStartElement("attributes");
			xsw.writeAttribute("attributeType", "part");
			xsw.writeEndElement(/* attributes */);
			
			xsw.writeEndElement(/* body */);
			xsw.writeEndElement(/* message */);

			xsw.writeEndDocument();
			xsw.flush();
			xml = baos.toString("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (xsw != null) {
					xsw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return xml;

	}

	private void wrap(XMLStreamWriter xsw, String tagName, String value) throws XMLStreamException {
		xsw.writeStartElement(tagName);
		xsw.writeCharacters(value);
		xsw.writeEndElement();
	}
}
