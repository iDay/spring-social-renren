package org.springframework.social.renren.api;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.renren.api.RenrenProfile;
import org.springframework.social.renren.api.json.RenrenModule;


public class RenrenProfileTestCase {
	MappingJacksonHttpMessageConverter converter;

	@Before
	public void setUp() throws Exception {
		converter = new MappingJacksonHttpMessageConverter();

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new RenrenModule());
		converter.setObjectMapper(objectMapper);
	}

	@After
	public void tearDown() throws Exception {
		converter = null;
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRenrenProfile() {
		try {
			List<RenrenProfile> renren = (List<RenrenProfile>) converter.read(RenrenProfileList.class,
					new HttpInputMessage() {

						@Override
						public HttpHeaders getHeaders() {
							return null;
						}

						@Override
						public InputStream getBody() throws IOException {
							return this.getClass().getResourceAsStream(
									"/user.json");
						}
					});
			assertEquals(1, renren.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class RenrenProfileList extends ArrayList<RenrenProfile> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7316943717530657260L;
		
	}

}
