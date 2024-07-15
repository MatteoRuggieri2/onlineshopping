package com.mr.onlineshopping.repository;

import com.mr.onlineshopping.entity.OrderArticle;
import com.mr.onlineshopping.entity.OrderArticleID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderArticleRepository extends JpaRepository<OrderArticle, OrderArticleID> {
    List<OrderArticle> findByOrderId(int orderId);
}
