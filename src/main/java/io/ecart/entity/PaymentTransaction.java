package io.ecart.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import io.ecart.enums.PaymentInstrument;
import io.ecart.enums.PaymentStatus;
import lombok.Data;

@Data
@Table(name = "payment_transaction", catalog = "ecart", schema = "")
@Entity
public class PaymentTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long orderId;
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	@Enumerated(EnumType.STRING)
	private PaymentInstrument paymentInstrument;
	
	private String paymentGatewayId;
	
	@CreationTimestamp
	private Instant createDate;
	
	@UpdateTimestamp
	private Instant updateDate;
}
