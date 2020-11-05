package io.ecart.product.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ecart.entity.ProductStock;
import io.ecart.product.repo.ProductStockRepository;

@Service
public class ProductStockService {

	@Autowired
	private ProductStockRepository productStockRepository;
	
	@Transactional
	public ProductStock add(long productId,int quantity) {
		Optional<ProductStock> productStockOpt = productStockRepository.findByProductId(productId);
		ProductStock productStock;
		if(productStockOpt.isPresent()) {
			productStock = productStockOpt.get();
			productStock.setAvailable(productStock.getAvailable()+quantity);
		}
		else {
			productStock = new ProductStock();
			productStock.setAvailable(quantity);
			productStock.setReserved(0);
		}
		productStock = productStockRepository.saveAndFlush(productStock);
		return productStock;
	}
	
	public void verifyIsInStock(long productId,int quantity) {
		ProductStock productStock = productStockRepository.findByProductId(productId).orElseThrow(()-> new RuntimeException("Not in stock"));
		if(productStock.getAvailable() < quantity)
			throw new RuntimeException("Not in stock");
	}
	
	public ProductStock reserve(long productId,int quantity) {
		Optional<ProductStock> productStockOpt = productStockRepository.findByProductId(productId);
		ProductStock productStock=null;
		if(productStockOpt.isPresent()) {
			productStock = productStockOpt.get();
			productStock.setAvailable(productStock.getAvailable()-quantity);
			productStock.setReserved(productStock.getReserved() + quantity);
			productStockRepository.save(productStock);
		}
		return productStock;
	}

	public ProductStock clearReservation(long productId,int quantity) {
		Optional<ProductStock> productStockOpt = productStockRepository.findByProductId(productId);
		ProductStock productStock=null;
		if(productStockOpt.isPresent()) {
			productStock = productStockOpt.get();
			productStock.setAvailable(productStock.getAvailable()+quantity);
			productStock.setReserved(productStock.getReserved() - quantity);
			productStockRepository.save(productStock);
		}
		return productStock;
	}
	
	
}
