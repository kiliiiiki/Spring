package org.zerock.sample;

import org.springframework.stereotype.Component;

@Component
public class Chef {

	private Chef() {
		System.out.println("Chef() call.");
	}
	
}
