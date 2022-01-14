package springbootvue.message;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springbootvue.common.AuthenticaitonResult;
import springbootvue.common.AuthenticationHelper;
import springbootvue.common.GatewayConfigurationProp;
import springbootvue.sso.TokenInterceptor;

@RestController
public class AuthenticationController {

	@Autowired
	private GatewayConfigurationProp configure;
	
	@Autowired
	private AuthenticationHelper helper;

	/**
	 * 
	 * @param authMode 认证方式，固定为cert
	 * @param random 随机数，前异步调用random接口的返回值
	 * @param signature random的数字签名
	 * @param pinCode 固定值
	 * @param key_pin 留空
	 * @return
	 */
    @RequestMapping(value = "/authenticate")
    public AuthenticaitonResult authenticate(@RequestParam(value = "authMode", required = false) String authMode,
            @RequestParam(value = "original", required = false) String original,
            @RequestParam(value = "signed_data", required = false) String signed_data,
            @RequestParam(value = "pinCode", required = false) String pinCode,
            @RequestParam(value = "key_pin", required = false) String key_pin, @RequestParam Map<String, String> params,
            @RequestBody String body, HttpServletRequest hrequest) {

        Map<String, String> request = new HashMap<String, String>();

        request.put("authMode", authMode);
        request.put("clientIP", "127.0.0.1");
        request.put("detach", signed_data);
        request.put("original", Base64.encodeBase64String(original.getBytes()));

        request.put("pinCode", pinCode);
        request.put("key_pin", key_pin);

        AuthenticaitonResult result = helper.authenticate(configure.buildGatewayUrl(), configure, request);
        // 认证成功
        if (StringUtils.pathEquals("false", result.getMessageState())) {
            // 将认证结果缓存，用于后续模拟校验token
            hrequest.getSession(true).setAttribute(TokenInterceptor.header_token, result);
        }
        return result;

    }

}
