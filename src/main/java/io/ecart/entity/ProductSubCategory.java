package io.ecart.entity;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "product_sub_category")
public class ProductSubCategory {

	@Id
	private String id;
	
	private String categoryId;
	
	private String name;
}
