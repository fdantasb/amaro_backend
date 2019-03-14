package br.com.fdantasb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fdantasb.data.ProductList;
import br.com.fdantasb.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/create/list", method = RequestMethod.POST)
	public ResponseEntity<Void> save (@RequestBody ProductList products)	{
		productService.createProductList(products.getProducts());
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
