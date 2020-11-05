package io.ecart.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ecart.dto.CreateOrderRequest;
import io.ecart.dto.CreateOrderResponse;
import io.ecart.entity.Order;
import io.ecart.order.repo.OrderItemRepository;
import io.ecart.order.repo.OrderRepository;
import io.ecart.payment.service.PaymentService;
import io.ecart.product.service.StockEventService;

@Service
public class OrderSevice {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private StockEventService stockEventService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Transactional
	public CreateOrderResponse placeOrder(CreateOrderRequest createOrderRequest) {
		Order order = createOrderRequest.toOrder();
		stockEventService.reserve(order);
		order = orderRepository.saveAndFlush(order);
		final Long orderId = order.getId();
		order.getOrderItems().forEach(item ->item.setOrderId(orderId));
		orderItemRepository.saveAll(order.getOrderItems());
		CreateOrderResponse createOrderResponse=new CreateOrderResponse();
		createOrderResponse.setOrderId(orderId);
		Long paymetTransactionId = paymentService.initiatePayment(order, createOrderRequest.getPaymentInstrument());
		createOrderResponse.setPaymentTransactionId(paymetTransactionId);
		shoppingCartService.clearCartForCustomerId(createOrderRequest.getCustomerId());
		return createOrderResponse;

	}

}
