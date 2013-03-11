/**
 * 
 */
package org.springframework.social.renren.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.renren.api.StatusPost;
import org.springframework.social.renren.api.json.RenrenModule;


/**
 * @author iday
 * 
 */
public class StatusPostTestCase {
	MappingJacksonHttpMessageConverter converter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		converter = new MappingJacksonHttpMessageConverter();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new RenrenModule());
		converter.setObjectMapper(objectMapper);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		converter = null;
	}

	/**
	 * Test method for
	 * {@link org.springframework.social.renren.api.StatusPost#StatusPost(long, org.springframework.social.renren.api.RenrenProfile, org.springframework.social.renren.api.Source, long, long, org.springframework.social.renren.api.Place)}
	 * .
	 */
	public void testStatusPostLongRenrenProfileSourceLongLongPlace() {
		try {
			StatusPost status = (StatusPost) converter.read(StatusPost.class,
					new HttpInputMessage() {

						@Override
						public HttpHeaders getHeaders() {
							return null;
						}

						@Override
						public InputStream getBody() throws IOException {
							return this.getClass().getResourceAsStream(
									"/status.json");
						}
					});
			assertEquals("人人Android", status);
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

}
