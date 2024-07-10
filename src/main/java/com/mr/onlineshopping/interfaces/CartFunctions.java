package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.exceptions.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public interface CartFunctions {
    Optional<Cart> getCartById(int cartId);
    Optional<Cart> getCartFromUserId(int userId);
    Set<Article> getCartArticles(int cartId) throws CartNotFound;
    boolean addArticleToUserCart(int userId, int articleId, int articleQta) throws UserNotFound, ArticleNotFound, ArticleNotAvailable, CartNotFound, CartAlreadyExists;
    boolean editArticleQtaToCart(int cartId, int articleId, int articleQta) throws ArticleNotFound, ArticleNotAvailable, CartNotFound, ToFewItemInTheCart, ArticleNotFoundInTheCart, UserNotFound;
    boolean deleteArticleToCart(int cartId, int articleId) throws ArticleNotFound, CartNotFound;
    boolean createCart(int userId) throws UserNotFound, CartAlreadyExists;
    boolean deleteCart(int cartId) throws CartNotFound;
    boolean updateCartTotalPrice(int cartId) throws CartNotFound;
}

