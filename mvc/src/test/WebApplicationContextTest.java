package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@ContextConfiguration({
	"file:WebContent\\WEB-INF\\root-context.xml",
	"file:WebContent\\WEB-INF\\servlet-context.xml"
})
@WebAppConfiguration()
public class WebApplicationContextTest {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void ba() {
		mockMvc = MockMvcBuilders.webAppContextSetup( context).build();
	}
	
	@Test
	void productListTest() throws Exception {
		String uri = "/productList";
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder
		= MockMvcRequestBuilders.get(uri);
		
		ResultActions resultActions1 = mockMvc.perform(mockHttpServletRequestBuilder);
		resultActions1.andDo(MockMvcResultHandlers.print());
		
		ResultActions resultActions2 = resultActions1.andExpect(status().isOk());
		
		ResultMatcher matcher = MockMvcResultMatchers.view().name("productList");
		
		resultActions2.andExpect(matcher);
	}
	@Test
	void loginTest() throws Exception {
		String uri = "/login";
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder
		= MockMvcRequestBuilders.get(uri);
		
		mockMvc.perform(mockHttpServletRequestBuilder);
	}
}
