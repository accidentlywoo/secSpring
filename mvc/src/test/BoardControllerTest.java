package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@ContextHierarchy({
	@ContextConfiguration(locations = "file:WebContent\\WEB-INF\\root-context.xml"),
	@ContextConfiguration(locations = "file:WebContent\\WEB-INF\\servlet-context.xml")
})
@WebAppConfiguration
public class BoardControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void ba() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	void productListTest() throws Exception {
		String uri = "/board/list/1";
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder
		= MockMvcRequestBuilders.get(uri);
		
		ResultActions resultActions1 = mockMvc.perform(mockHttpServletRequestBuilder);
		resultActions1.andDo(MockMvcResultHandlers.print());
		resultActions1.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
