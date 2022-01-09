package springbootvue.common;

import org.junit.Assert;
import org.junit.Test;

public class RandomControllerTest {

	@Test
	public void testBuildUrl() {
		String url_template = "%s://%s:%d/MessageService";
		String url = String.format(url_template, "http", "192.168.1.1", 6180);

		Assert.assertEquals("URL build error", "http://192.168.1.1:6180/MessageService", url);
		System.out.println(url);
	}
}
