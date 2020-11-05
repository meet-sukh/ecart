package io.ecart.order.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ecart.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findByCustomerId(Long customerId);
	
	Long deleteByCustomerId(Long customerId);
	
	Optional<CartItem> findByCustomerIdAndProductId(Long customerId,Long productId);
	
	Long deleteByCustomerIdAndProductId(Long customerId,Long productId);
}
