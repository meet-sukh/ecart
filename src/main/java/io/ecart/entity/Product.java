package io.ecart.entity;

import java.time.Instant;
import java.util.Map;

import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(value = "product")
public class Product {

	@Id
	private String id;
	
	private Long productId;
	
	private String name;
	
	private String description;
	
	private Double cost;
	
	private Long sellerId;
	
	private String productCategoryId;
	
	private String productSubCategoryId;
	
	private Map<String,Object> attributes;
	
	@CreationTimestamp
	private Instant createDate;
	
	@UpdateTimestamp
	private Instant updateDate; 
	
	@Transient
	private Integer quantity;
}
