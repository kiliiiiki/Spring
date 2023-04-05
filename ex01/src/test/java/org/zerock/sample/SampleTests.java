package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTests {

	//Restaurant 객체가 주입이 되는지를 확인해서 객체정보를 출력
	
	@Setter(onMethod_ = @Autowired)
	private Restaurant restaurant; //=new Restaurant() ;
	
	@Test
	public void testExist() {
		assertNotNull(restaurant);//restaurant 변수에 null 여부 체크
		
		log.info(restaurant);
		log.info("-----------");
		log.info(restaurant.getChef()); //restaurant 호출 시 chef 인스턴스 할당
	}
	
}
