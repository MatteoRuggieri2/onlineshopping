package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.dto.OrderDTO;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.Order;
import com.mr.onlineshopping.enums.PaymentType;
import com.mr.onlineshopping.exceptions.*;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.interfaces.OrderFunctions;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.repository.CartRepository;
import com.mr.onlineshopping.repository.OrderRepository;
import com.mr.onlineshopping.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {

    // Autowired del mapper per i DTO
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderFunctions orderService;

    @Autowired
    private CartFunctions cartService;

    @Autowired
    private UserFunctions userService;

    @Autowired
    private CartRepository cartRepository;

    // GET User Orders
    @GetMapping("users/{userId}/orders")
    public ResponseEntity<ApiResponse<List<OrderDTO>>> getUserOrders(@PathVariable("userId") int userId) throws UserNotFound {
        List<Order> orders = orderService.getOrdersByUserId(userId);

        // Per ogni articolo del set lo devo mappare in ArticleDTO e salvarlo in un nuovo set
        List<OrderDTO> ordersDTO = orders.stream()
                .map(article -> modelMapper.map(article, OrderDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(ordersDTO, "Success", HttpStatus.OK.value()));

    }

    // GET Order by order_id
    @GetMapping("orders/{orderId}")
    public ResponseEntity<ApiResponse<OrderDTO>> getUserOrderById(@PathVariable("orderId") int orderId) throws OrderNotFound {
        Order order = orderService.getOrderById(orderId).orElseThrow(() -> new OrderNotFound(orderId));
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return ResponseEntity.ok(new ApiResponse<>(orderDTO, "Success", HttpStatus.OK.value()));

    }

    // GET User Orders
    // TODO: Da finire (prima aggiungeva ordine e articoli senza rimuovere il carrello, ora non fa più nulla)
    @PostMapping("users/{userId}/buy-cart")
    public ResponseEntity<ApiResponse<OrderDTO>> createNewOrder(@PathVariable("userId") int userId,
                                                                @RequestParam("paymentType") PaymentType paymentType) throws UserNotFound, CartNotFound, OrderNotCreated, ArticleNotFound, ArticleNotAvailable {

        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
        Cart userCart = cartService.getCartFromUserId(userId).orElseThrow(() -> new CartNotFound("The user's cart was not found"));
        Order order = orderService.makeNewOrderByCartId(userCart.getId(), paymentType);
//        cartRepository.deleteById(userCart.getId());
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return ResponseEntity.ok(new ApiResponse<>(orderDTO, "Success", HttpStatus.OK.value()));

    }

}
