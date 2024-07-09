package com.mr.onlineshopping.repository;

import com.mr.onlineshopping.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(int userId);

    // Questo metodo aggiorna il prezzo del carrello
    @Modifying
    @Transactional
    @Query(value = "UPDATE carts SET total_price = :totalPrice WHERE id = :cartId", nativeQuery = true)
    void updateCartTotalPrice(@Param("cartId") int cartId,
                              @Param("totalPrice") BigDecimal totalPrice);
}
