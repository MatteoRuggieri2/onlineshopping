package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.UserNotFound;

import java.util.Optional;
import java.util.Set;

public interface CartFunctions {
    Optional<Cart> getCartById(int cartId);
    Optional<Cart> getCartFromUserId(int userId);
    Set<Article> getCartArticles(int cartId) throws CartNotFound;
    boolean addArticleToCart(int cartId, int articleId, int articleQta);
    boolean removeArticleToCart(int cartId, int articleId, int articleQta);
    boolean createCart(int userId) throws UserNotFound;
    boolean clearCart(int cartId);
    boolean deleteCart(int cartId) throws CartNotFound;
}

