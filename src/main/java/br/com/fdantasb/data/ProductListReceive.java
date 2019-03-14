package br.com.fdantasb.data;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.fdantasb.model.Product;

public class ProductList implements Serializable{

	private static final long serialVersionUID = 2459338070617866447L;
	
	private ArrayList<Product> products;

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
}
