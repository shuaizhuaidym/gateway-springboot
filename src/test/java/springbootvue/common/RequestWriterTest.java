package springbootvue.common;

import org.junit.Test;

import springbootvue.common.xml.UserWriter;

public class RequestWriterTest {
	@Test
	public void testWriteRquest() {
		String request = UserWriter.instance().writeRquest();
		System.out.println(request);
	}

}
