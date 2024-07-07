package com.mr.onlineshopping.dto;

import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Cart cart;
    private List<Order> orders;
}
