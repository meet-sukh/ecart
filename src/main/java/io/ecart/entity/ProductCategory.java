package io.ecart.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "product_category")
public class ProductCategory {

	@Id
	private String id;
	
	private String name;
}
