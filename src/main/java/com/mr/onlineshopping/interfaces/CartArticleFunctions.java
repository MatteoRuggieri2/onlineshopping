package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.entity.CartArticleID;
import com.mr.onlineshopping.exceptions.ArticleNotFoundInTheCart;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.UserNotFound;

import java.util.List;

public interface CartArticleFunctions {
    List<CartArticle> getCartArticlesByCartId(int cartId) throws CartNotFound;
    List<CartArticle> getCartArticlesByUserId(int userId) throws UserNotFound, CartNotFound;
    int getCartArticleQta(CartArticleID cartArticleID) throws ArticleNotFoundInTheCart;
}
