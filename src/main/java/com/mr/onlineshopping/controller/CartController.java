package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.dto.ArticleDTO;
import com.mr.onlineshopping.dto.CartDTO;
import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.UserNotFound;
import com.mr.onlineshopping.interfaces.CartFunctions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CartController {

    // Autowired del mapper per i DTO
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartFunctions cartService;

    // GET Cart by cart_id
    @GetMapping("cart/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable("cartId") int cartId) throws CartNotFound {
        Cart cart = cartService.getCartFromUserId(cartId)
                .orElseThrow(() -> new CartNotFound(cartId));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(cartDTO);
    }

    // GET Cart articles
    @GetMapping("cart/{cartId}/articles")
    public ResponseEntity<Set<ArticleDTO>> getCartArticles(@PathVariable("cartId") int cartId) throws CartNotFound {
        Set<Article> cartArticles = cartService.getCartArticles(cartId);

        // Per ogni articolo del set lo devo mappare in ArticleDTO e salvarlo in un nuovo set
        Set<ArticleDTO> cartArticlesDTO = cartArticles.stream()
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .collect(Collectors.toSet());

        return ResponseEntity.ok(cartArticlesDTO);
    }

    // CREATE Cart
    @PostMapping("cart/create/{userId}")
    public ResponseEntity<Boolean> createCart(@PathVariable("userId") int userId) throws UserNotFound {
        cartService.createCart(userId);
        return ResponseEntity.ok(true);
    }

    // DELETE Cart
    @DeleteMapping("cart/{cartId}/delete")
    public ResponseEntity<Boolean> deleteCartById(@PathVariable("cartId") int cartId) throws CartNotFound {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok(true);
    }
}
