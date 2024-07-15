package com.mr.onlineshopping.interfaces;

import com.mr.onlineshopping.entity.OrderArticle;
import com.mr.onlineshopping.entity.OrderArticleID;
import com.mr.onlineshopping.exceptions.ArticleNotFoundInTheOrder;
import com.mr.onlineshopping.exceptions.OrderNotFound;

import java.util.List;
import java.util.Optional;

public interface OrderArticleFunctions {
    boolean existById(OrderArticleID orderArticleID);
//    Optional<CartArticle> getOrderArticleById(OrderArticleID orderArticleID);
    List<OrderArticle> getOrderArticlesByOrderId(int orderId) throws OrderNotFound;
    int getOrderArticleQta(OrderArticleID orderArticleID) throws ArticleNotFoundInTheOrder;
    boolean saveNewOrderArticle(OrderArticle orderArticle);

    void saveAllAndFlushOrderArticleList(List<OrderArticle> orderArticles);
}
