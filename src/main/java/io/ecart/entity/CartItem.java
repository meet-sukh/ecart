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
@Entity
@Table(name = "cart_item", catalog = "ecart", schema = "")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long customerId;
	
	private Integer quantity;	
	
	private Long productId;
	
	@CreationTimestamp
	private Instant createDate;
	
	@UpdateTimestamp
	private Instant updateDate;
}
