package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.entity.CartArticleID;
import com.mr.onlineshopping.exceptions.ArticleNotFoundInTheCart;
import com.mr.onlineshopping.exceptions.CartAlreadyExists;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.UserNotFound;

import java.util.List;
import java.util.Optional;

public interface CartArticleFunctions {
    boolean existById(CartArticleID cartArticleID);
    boolean existByArticleIdAndUserId(int articleId, int userId) throws UserNotFound, CartAlreadyExists;
    Optional<CartArticle> getCartArticleById(CartArticleID cartArticleID);
    List<CartArticle> getCartArticlesByCartId(int cartId) throws CartNotFound;
    List<CartArticle> getCartArticlesByUserId(int userId) throws UserNotFound, CartNotFound;
    int getCartArticleQta(CartArticleID cartArticleID) throws ArticleNotFoundInTheCart;
    boolean saveNewCartArticle(CartArticle cartArticle);
    boolean deleteCartArticle(CartArticleID cartArticleID);
    int deleteAllCartArticles(int cartId);
}
