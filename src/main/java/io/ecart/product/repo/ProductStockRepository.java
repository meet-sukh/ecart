package io.ecart.product.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ecart.entity.ProductStock;

public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {

	Optional<ProductStock> findByProductId(Long productId); 
}
