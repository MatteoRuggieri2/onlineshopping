package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Order;
import com.mr.onlineshopping.enums.OrderStatus;

import java.util.List;

public interface OrderFunctions {
    Order getOrderById(int orderId);
    List<Order> getOrdersByUserId(int userId);
    boolean makeNewOrderByCartId(int cartId);
    boolean setOrderStatus(int orderId, OrderStatus orderStatus);
}
