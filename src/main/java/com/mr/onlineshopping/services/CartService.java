package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartFunctions {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Optional<Cart> getCartById(int cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Optional<Cart> getCartFromUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public List<Article> getCartArticles(int cartId) throws Exception {
        // Mi prendo il cart
        Cart cart = this.getCartById(cartId).orElseThrow(() -> new Exception("Cart not found"));

        // Mi prendo gli articoli dal cart
        return List.of();
    }

    @Override
    public boolean addArticleToCart(int cartId, int articleId, int articleQta) {
        return false;
    }

    @Override
    public boolean removeArticleToCart(int cartId, int articleId, int articleQta) {
        return false;
    }

    @Override
    public boolean clearCart(int cartId) {
        return false;
    }

    @Override
    public boolean deleteCart(int cartId) {
        return false;
    }
}
