package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	
	public Restaurant() {
		System.out.println("Restaurant() call.");
	}
	
	@Setter(onMethod_ = @Autowired)
	private Chef chef; // @Autowired: 스프링에서 관리하는 객체 주입. get/set
	
}
