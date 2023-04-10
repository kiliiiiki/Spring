package com.yedam.board.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.board.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value="/getText", produces = "text/plain;charset=UTF-8")
	public String getText() {
		return "안녕하세요";
	}
	
	@GetMapping(value="/getSample", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,//
			MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		SampleVO vo = new SampleVO(100, "길동", "홍");
		return vo;
	}
	
	@GetMapping(value="/getSample2")
	public SampleVO getSample2() {
		SampleVO vo = new SampleVO(100, "길동", "홍");
		return vo;
	}
	
	@GetMapping(value="/getList")
	public List<SampleVO> getList(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			list.add(new SampleVO(i+10, "firstName"+i, "lastName"+i));
		}
		return list;
	}
	
	@GetMapping("/product/{cat}/{pid}") //attribute가 여러개면 value=?, produces=? 등으로 지정해줘야함
										//url경로상의 변수지정. product?cat=bags&pid=1001 (cat, pid는 url아니고 파라미터)
	public String[] getPath(@PathVariable("cat") String cat//
	, @PathVariable("pid") Integer pid) {
		return new String[] {
				"Category: "+cat, "productId: "+pid
		};
	}
	
	//컨트롤에 key=value 형식으로 값을 전달했었음
	//json 형식의 값을 전달 (@RequestBody)
	
	@PostMapping("/sample")
	public SampleVO convert(@RequestBody SampleVO sample) {
		
		return sample;
	}
	
}
