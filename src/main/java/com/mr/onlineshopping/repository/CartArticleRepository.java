package com.mr.onlineshopping.repository;

import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.entity.CartArticleID;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartArticleRepository extends JpaRepository<CartArticle, CartArticleID> {

    // Con questo metodo vado ad aggiornare la qta di un'articolo nel carrello
    @Modifying
    @Transactional
    @Query(value = "UPDATE cart_article SET article_qta = :articleQta WHERE cart_id = :cartId AND article_id = :articleId", nativeQuery = true)
    int updateCartArticleQtaToCart(@Param("cartId") int cartId,
                                   @Param("articleId") int articleId,
                                   @Param("articleQta") int articleQta);

    // Questo metodo seleziona tutti gli articoli di un carrello
    @Query(value = "SELECT * FROM cart_article WHERE cart_id = :cartId", nativeQuery = true)
    List<CartArticle> findByCartId(@Param("cartId") int cartId);
}
