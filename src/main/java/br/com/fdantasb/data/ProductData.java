package br.com.fdantasb.data;

import br.com.fdantasb.model.Product;

public class ProductData extends Product{

	private static final long serialVersionUID = -889558389053031002L;
	
	private String[] tagsVector = new String[20];

	public String[] getTagsVector() {
		return tagsVector;
	}

	public void setTagsVector(String[] tagsVector) {
		this.tagsVector = tagsVector;
	}
	
	
	
}
