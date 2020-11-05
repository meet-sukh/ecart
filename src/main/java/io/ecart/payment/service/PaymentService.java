package io.ecart.payment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.ecart.entity.Order;
import io.ecart.entity.PaymentTransaction;
import io.ecart.enums.PaymentInstrument;
import io.ecart.enums.PaymentStatus;
import io.ecart.order.service.OrderEventService;
import io.ecart.payment.repo.PaymentTransactionRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentTransactionRepository paymentTransactionRepository;
	
	@Autowired
	private OrderEventService orderEventService;

	@Transactional
	public Long initiatePayment(Order order, PaymentInstrument paymentInstrument) {

		PaymentTransaction paymentTransaction = new PaymentTransaction();
		paymentTransaction.setOrderId(order.getId());
		paymentTransaction.setPaymentStatus(PaymentStatus.INITIATED);
		paymentTransaction.setPaymentInstrument(paymentInstrument);
		paymentTransaction = paymentTransactionRepository.save(paymentTransaction);
		// payment gateway logic
		Long paymetTransactionId = paymentTransaction.getId();
		return paymetTransactionId;
	}

	@Transactional
	public void processPaymentSucess(Long paymetTransactionId) {
		Optional<PaymentTransaction> paymentTransactionOpt = paymentTransactionRepository.findById(paymetTransactionId);
		if(paymentTransactionOpt.isPresent()) {
			PaymentTransaction paymentTransaction = paymentTransactionOpt.get();
			paymentTransaction.setPaymentStatus(PaymentStatus.SUCCESS);
			paymentTransactionRepository.save(paymentTransaction);
			//call order to placed
			orderEventService.processSucessOrderStatus(paymentTransaction.getOrderId());
		}
	}

	public void processPaymentFailure(Long paymetTransactionId) {
		Optional<PaymentTransaction> paymentTransactionOpt = paymentTransactionRepository.findById(paymetTransactionId);
		if(paymentTransactionOpt.isPresent()) {
			PaymentTransaction paymentTransaction = paymentTransactionOpt.get();
			paymentTransaction.setPaymentStatus(PaymentStatus.FAILED);
			paymentTransactionRepository.save(paymentTransaction);
			//call order to cancel
			orderEventService.processFailedOrderStatus(paymentTransaction.getOrderId());
		}
	}
}
