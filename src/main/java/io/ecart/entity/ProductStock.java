package io.ecart.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Table(name = "product_stock", catalog = "ecart", schema = "")
@Entity
public class ProductStock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	
	private Integer available=0;
	
	private Integer reserved=0;
	
	@CreationTimestamp
	private Instant createDate;
	
	@UpdateTimestamp
	private Instant updateDate;

}
