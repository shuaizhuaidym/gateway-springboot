package springbootvue.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootvue.common.GatewayConfigurationProp;
import springbootvue.common.RandomHelper;

/**
 * random service
 * 
 * @author yanming_dai
 *
 */
@RestController
public class RandomController {

	@Autowired
	private GatewayConfigurationProp configure;

	int random_length = 32;

	@RequestMapping(value="/random")
	public Map<String, Object> random() throws IOException, ServletException {
		boolean pinCode = configure.isPin_code();
		int randomSource = configure.getRandom_source();

		String random = null;
		random = random2(randomSource);

		Map<String, Object> map = new HashMap<>();
		map.put("random", random);
		map.put("pinCode", pinCode);

		return map;
	}

	private String random2(int source) throws UnsupportedEncodingException, HttpException, IOException {
		RandomHelper helper = RandomHelper.instance();
		if (source == 1) {
			return RandomHelper.instance().randomLocal(random_length);
		} else if (source == 2) {
			String appID = configure.getAppID();
			return helper.randomRemote(configure.buildGatewayUrl(), appID);
		} else {
			throw new RuntimeException("Random source confiure invalid.");
		}

	}

}
