package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.my.vo.Customer;
import com.my.vo.Person;

class JUnitTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLombokPersonVO() {
//		fail("Not yet implemented"); 무조건 테스트 실패
		Person p1, p2;
		p1 = new Person("n1", "a1");
		p2 = new Person();
		assertEquals(p1.getName(), "n1");
	}
	
	@Test
	void testLombokCustomerVO() {
		Customer c1, c2;
		c1 = new Customer("id1", "p1");
		c2 = new Customer("id2", "p2");
		
		assertFalse(c1 == c2);
		assertNotSame(c1, c2); //assertFalse 와 같은 효과
		
		assertNotNull(c1);
		assertNotEquals(c1, c2);// equals() 오버라이딩
		
		// 테스트 실패시 1번째 실패에서 테스트 종료된다. -> 여러개를 테스트 하고 싶을 땐?
		assertNotNull(c1.getName());
		assertNotNull(c1.getAddr());
	}

}
