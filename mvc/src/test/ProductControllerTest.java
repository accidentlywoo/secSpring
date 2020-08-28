package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.my.controller.ProductController;

@ExtendWith(SpringExtension.class)
@ContextHierarchy({
	@ContextConfiguration(locations = "file:WebContent\\WEB-INF\\root-context.xml"),
	@ContextConfiguration(locations = "file:WebContent\\WEB-INF\\servlet-context.xml")
})
@WebAppConfiguration
public class ProductControllerTest {
	@Autowired
	private ProductController productController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".jsp");
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController)
				.setViewResolvers(viewResolver).build();
	}
	
	@Test
	void productListTest() throws Exception {
		String uri = "/productList";
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder
		= MockMvcRequestBuilders.get(uri);
		
		ResultActions resultActions1 = mockMvc.perform(mockHttpServletRequestBuilder);
		resultActions1.andDo(MockMvcResultHandlers.print());
		
		ResultActions resultActions2 = resultActions1.andExpect(MockMvcResultMatchers.status().isOk());
		
		ResultMatcher matcher = MockMvcResultMatchers.view().name("productList");
		resultActions2.andExpect(matcher);
	}
	
	@Test
	void productDetailTest() throws Exception {
		String uri = "/productDetail?prod_no=C0001";
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder
		= MockMvcRequestBuilders.get(uri);
		
		ResultActions resultActions1 = mockMvc.perform(mockHttpServletRequestBuilder);
		resultActions1.andDo(MockMvcResultHandlers.print());
		
		resultActions1.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"));
	}
}
