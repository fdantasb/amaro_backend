package br.com.fdantasb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
}
