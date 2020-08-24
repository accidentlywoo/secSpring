package test;

import static org.junit.Assume.assumeThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.my.vo.Customer;
import com.my.vo.Person;

class JUnitTest {
	private static Person p1, p2;
	private static Customer c1, c2;
	private int num;
	@BeforeAll
	static void beforAll() {
		System.out.println("@BeforeAll : 모든 테스트메서드 이전에 1회 호출");
		p1 = new Person("n1", "a1");
		p2 = new Person();
		
		c1 = new Customer("id1", "p1");
		c2 = new Customer("id2", "p2");
	}
	
	@BeforeEach
	void beforEach() throws Exception {
		System.out.println("@BeforeEach : 각 테스트메서드 이전에 호출됨");
		num = 10;
	}

	@Test
	@DisplayName(value = "롬복문 라이브러리 테스트 : 단정문(assert)")
	void testLombokPersonVO() {
//		fail("Not yet implemented"); 무조건 테스트 실패
		Person p1, p2;
		p1 = new Person("n1", "a1");
		p2 = new Person();
		assertEquals(p1.getName(), "n1");
	}
	
	@Test
	@DisplayName(value = "롬복문 라이브러리 테스트2 : 단정문(assert)")
	void testLombokCustomerVO() {
		Customer c1, c2;
		c1 = new Customer("id1", "p1");
		c2 = new Customer("id2", "p2");
		
		assertFalse(c1 == c2);
		assertNotSame(c1, c2); //assertFalse 와 같은 효과
		
		assertNotNull(c1);
		assertNotEquals(c1, c2);// equals() 오버라이딩
		
		// 테스트 실패시 1번째 실패에서 테스트 종료된다. -> 여러개를 테스트 하고 싶을 땐?
//		assertNotNull(c1.getName());
//		assertNotNull(c1.getAddr());
		
		// Collection을 이용해서 Multiple Error를 방생시키자.
		assertAll(
				() -> assertNotNull(c1.getName()),
				() -> assertNotNull(c1.getAddr())
		);
	}
	
	@Test
	@DisplayName(value = "가정문(assuming)")
	void testAssume() {
		int num = 10;
		assumingThat(num % 2 == 0, () -> assertEquals(num, 10));
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("@AfterEach : 각 테스트메서드 이후에 호출됨");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("@AfterAll : 모든 테스트메서드 호출된 이후 1번 호출");
	}
}
