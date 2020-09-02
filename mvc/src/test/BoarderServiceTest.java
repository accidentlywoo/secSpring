package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.my.dao.BoardDAO;
import com.my.exception.FindException;
import com.my.model.PageBean;
import com.my.service.BoardService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:WebContent\\WEB-INF\\root-context.xml")
public class BoarderServiceTest {
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private BoardService boardservice;
	
	@Test
	void selectAll() {
		try {
			boardDAO.selectAll(1, 4);
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void selectCount() {
		try {
			assertTrue(boardDAO.selectCount() == 22);
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void 페이지_리스트_에러_테스트() {
		assertThrows(FindException.class, ()-> {
			boardservice.findAll(0);
		});
	}
	@Test
	void 페이지_리스트_조회() {
		try {
			 //given

	        //현재페이지 요청이 0이 와도 1로 세팅
				assertTrue(boardservice.findAll(1).getList().size() == PageBean.CNT_PER_PAGE);
	        //then
			
		} catch (FindException e) {
			fail(e.getMessage());
		}
	}
}
