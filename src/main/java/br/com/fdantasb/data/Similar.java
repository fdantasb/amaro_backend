package br.com.fdantasb.data;

import java.io.Serializable;

public class Similar implements Serializable{

	private static final long serialVersionUID = -867437255905866447L;
	
	private Long id;
	private String name;
	private double similarity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSimilarity() {
		return similarity;
	}
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
