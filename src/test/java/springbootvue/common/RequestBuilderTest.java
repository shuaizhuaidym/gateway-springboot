package springbootvue.common;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import springbootvue.common.xml.RequestBuilder;

public class RequestBuilderTest {
	@Test
	public void testBuildRequest() {
		GatewayConfigurationProp configure = new GatewayConfigurationProp();
		configure.setMessage_version("1.1");
		configure.setAccess_control("false");

		Map<String, String> request = new HashMap<>();

		request.put("clientIP", "192.168.9.128");
		request.put("appId", "UIAS");
		request.put("detach", "MIID0AYJKoZIhvcNAQcCoIIDwTCCA70CAQExCzAJBgUrDgMCGgUAMAsGC");
		request.put("original", "9527");

		String requestXml = RequestBuilder.instance().buildRequest(configure, request);
		
		Assert.assertTrue(requestXml.endsWith("</body></message>"));
		System.out.println(requestXml);
	}

}
