package io.ecart.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ecart.entity.PaymentTransaction;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {

}
