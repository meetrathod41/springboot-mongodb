package rc;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SpringbootMongodbApplicationTests {

	@Autowired
	protected MockMvc mockMvc;
	@Mock
	private HotelRepository hotelRepository;
	@InjectMocks
	private HotelController hotelController;
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
		assertEquals(200, status);
	}

	@Test
	public void deleteByIdTest() throws Exception {
		String uri = "/hotels/609d0734ad8d7d65c27da7e5";
		MvcResult mvcResult = mockMvc.perform(delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}

	@Test
	public void getByPricePerNightTest() throws Exception {

		MvcResult result= mockMvc.perform(get("/hotels/price/100")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Ibis")))
				.andExpect(jsonPath("$[0].pricePerNight", is(90)))
				.andExpect(jsonPath("$[0].address.city", is("Bucharest")))
				.andExpect(jsonPath("$[0].address.country", is("Romania")))
				.andReturn();

	}
	@Test
	public void getByCityTest() throws Exception {

		MvcResult result= mockMvc.perform(get("/hotels/address/Paris")
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Marriot")))
				.andExpect(jsonPath("$[0].pricePerNight", is(130)))
				.andExpect(jsonPath("$[0].address.city", is("Paris")))
				.andExpect(jsonPath("$[0].address.country", is("France")))
				.andExpect(jsonPath("$[0].reviews[0].userName", is("John")))
				.andReturn();

	}

}
