package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartFunctions {
    Optional<Cart> getCartById(int cartId);
    Optional<Cart> getCartFromUserId(int userId);
    List<Article> getCartArticles(int cartId);
    boolean addArticleToCart(int cartId, int articleId, int articleQta);
    boolean removeArticleToCart(int cartId, int articleId, int articleQta);
    boolean clearCart(int cartId);
    boolean deleteCart(int cartId);
}

