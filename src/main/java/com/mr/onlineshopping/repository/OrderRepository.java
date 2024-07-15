package com.mr.onlineshopping.repository;

import com.mr.onlineshopping.entity.Order;
import com.mr.onlineshopping.enums.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(int userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET state = :orderStatus WHERE id = :orderId", nativeQuery = true)
    int setStateByOrderId(int orderId, OrderStatus orderStatus);
}
