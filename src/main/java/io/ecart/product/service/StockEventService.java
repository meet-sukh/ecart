package io.ecart.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ecart.entity.Order;

@Service
public class StockEventService {

	@Autowired
	private ProductStockService productStockService;
	
	@Transactional
	public void reserve(Order order) {
		order.getOrderItems().stream().forEach(item ->{
			productStockService.verifyIsInStock(item.getProductId(), item.getQuantity());
			productStockService.reserve(item.getProductId(), item.getQuantity());
		});
	}
}
