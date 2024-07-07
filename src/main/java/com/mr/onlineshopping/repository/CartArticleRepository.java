package com.mr.onlineshopping.repository;

import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.entity.CartArticleID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartArticleRepository extends JpaRepository<CartArticle, CartArticleID> {
//    boolean deleteWhereCartId
}
