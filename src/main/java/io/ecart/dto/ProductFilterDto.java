package io.ecart.dto;

import lombok.Data;

@Data
public class ProductFilterDto {
	
	private String categoryId;
	
	private String subCategoryId;
	
	private Float minPrice;
	
	private Float maxPrice;
	
	private String searchText;
}
