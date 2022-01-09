package springbootvue.message;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springbootvue.common.AuthenticaitonResult;
import springbootvue.common.AuthenticationHelper;
import springbootvue.common.GatewayConfigurationProp;

@RestController
public class AuthenticationController {

	@Autowired
	private GatewayConfigurationProp configure;

	/**
	 * 
	 * @param authMode
	 * @param random
	 * @param signature
	 * @param pinCode
	 * @param key_pin
	 * @return
	 */
	@RequestMapping(value = "/authenticate", produces = "application/json; charset=utf-8")
	public AuthenticaitonResult authenticate(String authMode, String original, String signed_data, String pinCode,
			String key_pin) {
		Map<String, String> request = new HashMap<String, String>();

		request.put("authMode", authMode);
		request.put("clientIP", "127.0.0。1");
		request.put("detach", signed_data);
		request.put("original", Base64.encodeBase64String(original.getBytes()));
		
		request.put("pinCode", pinCode);
		request.put("key_pin", key_pin);
		
		AuthenticaitonResult result = new AuthenticationHelper().authenticate(configure.buildGatewayUrl(), configure,
				request);
		return result;

	}

}
