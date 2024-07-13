package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.exceptions.*;

import java.util.List;
import java.util.Optional;

public interface CartFunctions {
    boolean ifCartExist(int cartId);
    Optional<Cart> getCartById(int cartId);
    Optional<Cart> getCartFromUserId(int userId);
    List<CartArticle> getCartArticles(int cartId) throws CartNotFound;
    boolean addArticleToUserCart(int userId, int articleId, int articleQta) throws UserNotFound, ArticleNotFound, ArticleNotAvailable, CartNotFound, CartAlreadyExists, InvalidSelectedArticleQta;
    boolean editArticleQtaToCart(int cartId, int articleId, int articleQta) throws UserNotFound, ArticleNotFound, ArticleNotAvailable, CartNotFound, ArticleNotFoundInTheCart;
    boolean deleteArticleToCart(int cartId, int articleId) throws ArticleNotFound, CartNotFound;
    //boolean updateCart(Cart cart);
    Cart createCart(int userId) throws UserNotFound, CartAlreadyExists;
    boolean clearCart(int cartId) throws CartNotFound;
    boolean deleteCart(int cartId) throws CartNotFound;
    boolean updateCartTotalPrice(int cartId) throws CartNotFound;
}

