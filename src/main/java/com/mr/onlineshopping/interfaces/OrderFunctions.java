package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.entity.Order;
import com.mr.onlineshopping.entity.OrderArticle;
import com.mr.onlineshopping.enums.OrderStatus;
import com.mr.onlineshopping.enums.PaymentType;
import com.mr.onlineshopping.exceptions.*;

import java.util.List;
import java.util.Optional;

public interface OrderFunctions {
    boolean existById(int orderId);
    Optional<Order> getOrderById(int orderId);
    List<Order> getOrdersByUserId(int userId) throws UserNotFound;
    Order makeNewOrderByCartId(int cartId, PaymentType paymentType) throws CartNotFound, OrderNotCreated, ArticleNotFound, ArticleNotAvailable;
    OrderArticle transformCartArticleToOrderArticle(CartArticle cartArticle, int orderId) throws ArticleNotFound, OrderNotFound;
    boolean saveOrderArticle(OrderArticle orderArticle);
    boolean setOrderStatus(int orderId, OrderStatus orderStatus) throws OrderNotFound;
}
