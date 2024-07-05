package com.mr.onlineshopping.repository;

import com.mr.onlineshopping.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
