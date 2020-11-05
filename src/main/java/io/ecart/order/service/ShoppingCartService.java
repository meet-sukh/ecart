package io.ecart.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ecart.dto.OrderItemDto;
import io.ecart.entity.CartItem;
import io.ecart.order.repo.CartRepository;

@Service
public class ShoppingCartService {
	
	@Autowired
	private CartRepository cartRepository;

	public List<OrderItemDto> getCart(Long customerId) {
		List<CartItem> cartItems = cartRepository.findByCustomerId(customerId);
		List<OrderItemDto> orderItemDtoList = cartItems.stream().map(cartItem -> {
			OrderItemDto orderItemDto = new OrderItemDto();
			orderItemDto.setProductId(cartItem.getProductId());
			orderItemDto.setQuantity(cartItem.getQuantity());
			return orderItemDto;
		}).collect(Collectors.toList());
		return orderItemDtoList;

	}

	public void addUpdateOrderItemToCart(OrderItemDto orderItem) {
		CartItem cartItem = cartRepository.findByCustomerIdAndProductId(orderItem.getCustomerId(), orderItem.getProductId()).orElse(new CartItem());
		cartItem.setCustomerId(orderItem.getCustomerId());
		cartItem.setProductId(orderItem.getProductId());
		cartItem.setQuantity(orderItem.getQuantity());
		cartRepository.save(cartItem);
	}

	public void removeOrderItemFromCart(OrderItemDto orderItem) {
		cartRepository.deleteByCustomerIdAndProductId(orderItem.getCustomerId(),orderItem.getProductId());	
	}
	
	@Transactional
	public void clearCartForCustomerId(Long customerId) {
		cartRepository.deleteByCustomerId(customerId);
	}

}
