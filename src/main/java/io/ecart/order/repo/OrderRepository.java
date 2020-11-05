package io.ecart.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ecart.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
