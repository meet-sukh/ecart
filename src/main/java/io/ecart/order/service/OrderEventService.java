package io.ecart.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ecart.entity.Order;
import io.ecart.enums.OrderStatus;
import io.ecart.order.repo.OrderRepository;

@Service
public class OrderEventService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	public void processSucessOrderStatus(Long orderId) {
		Optional<Order> orderOpt = orderRepository.findById(orderId);
		if(orderOpt.isPresent()) {
			Order order = orderOpt.get();
			order.setStatus(OrderStatus.CREATED);
			orderRepository.save(order);
		}
	}
	
	public void processFailedOrderStatus(Long orderId) {
		Optional<Order> orderOpt = orderRepository.findById(orderId);
		if(orderOpt.isPresent()) {
			Order order = orderOpt.get();
			order.setStatus(OrderStatus.CANCELLED);
			orderRepository.save(order);
		}
	}
}
