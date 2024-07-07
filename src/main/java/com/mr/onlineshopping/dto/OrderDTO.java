package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.User;
import com.mr.onlineshopping.enums.OrderStatus;
import com.mr.onlineshopping.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private int id;
    private BigDecimal totalPrice;
    private PaymentType paymentType;
    private OrderStatus state;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private User user;
    private Set<Article> articles = new HashSet<>();
}
