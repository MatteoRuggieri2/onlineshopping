package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Order;
import com.mr.onlineshopping.enums.OrderStatus;
import com.mr.onlineshopping.interfaces.OrderFunctions;
import com.mr.onlineshopping.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderFunctions {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order getOrderById(int orderId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return List.of();
    }

    @Override
    public boolean makeNewOrderByCartId(int cartId) {
        return false;
    }

    @Override
    public boolean setOrderStatus(int orderId, OrderStatus orderStatus) {
        return false;
    }
}
