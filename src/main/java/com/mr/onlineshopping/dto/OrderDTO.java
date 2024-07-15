package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.OrderArticle;
import com.mr.onlineshopping.entity.User;
import com.mr.onlineshopping.enums.OrderStatus;
import com.mr.onlineshopping.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
    private UserDTO user; // In questo modo non vedo la password
//    private Set<Article> articles;

//  TODO: Per la relazione one to many con la tabella ponte
    private List<OrderArticleDTO> orderArticles;   // -> Questo crea l'errore
}
