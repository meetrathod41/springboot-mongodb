package rc;




import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rc.Hotel;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SpringbootMongodbApplicationTests {

	protected MockMvc mockMvc;
	@MockBean
	private HotelRepository repository;
	@Autowired
	private WebApplicationContext context;

	ObjectMapper om = new ObjectMapper();

	@Before
	public void setUp(){
		mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
	}

    @Test
    public void getAllTest() throws Exception {
		MvcResult result = mockMvc.perform(get("/hotels/all")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = result.getResponse().getStatus();
		System.out.println(status);
		assertEquals(200, status);
		//String resultContent = result.getResponse().getContentAsString();
		//System.out.println(resultContent);
	//	Response response = om.readValue(resultContent, Response.class);
	//	Assert.assertTrue(response.isStatus() == Boolean.TRUE);


	}

//		Hotel hotel =new Hotel(
//				"Ibis",
//				90,
//				new Address("Bucharest","Romania"),
//				Arrays.asList(
//						new Reviews("Teddy", 9, false)
//
//				)
//		);
//		doReturn(Optional.of(hotel)).when(repository).findById("609a19d5f2b9de688a4dc35a");
//
//		mockMvc.perform(get("/hotels/{id}",1L))
//				// Validate the response code and content type
//				.andExpect(status().isOk())
//			//	.andExpect(content(MediaType.APPLICATION_JSON_VALUE))
//
//				// Validate headers
//				.andExpect(header().string(HttpHeaders.LOCATION, "/hotels/609a19d5f2b9de688a4dc35a"))
//				//.andExpect(header().string(HttpHeaders.ETAG, "\"1\""))
//
//				// Validate the returned fields
//				.andExpect((ResultMatcher) jsonPath("$.id", is("609a19d5f2b9de688a4dc35a")))
//				.andExpect((ResultMatcher) jsonPath("$.name", is("Ibis")))
//				.andExpect((ResultMatcher) jsonPath("$.pricePerNight", is(90)))
//				.andExpect((ResultMatcher) jsonPath("$.address.city", is("Bucharest")))
//				.andExpect((ResultMatcher) jsonPath("$.address.country", is("Romania")));
//
//}

}
