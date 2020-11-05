package io.ecart.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ecart.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
