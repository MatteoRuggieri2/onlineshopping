package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.User;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.UserNotFound;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.repository.CartRepository;
import com.mr.onlineshopping.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService implements CartFunctions {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<Cart> getCartById(int cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public Optional<Cart> getCartFromUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Set<Article> getCartArticles(int cartId) throws CartNotFound {
        // Mi prendo il cart, se non c'è lancio exception custom
        Cart cart = this.getCartById(cartId).orElseThrow(() -> new CartNotFound(cartId));
        return cart.getArticles();
    }

    /* Posso aggiungere al carrello un articolo disponibile.
    * Posso aggiungerne massimo il numero disponibile */
    @Override
    public boolean addArticleToCart(int cartId, int articleId, int articleQta) {
        // Controllo se l'articolo esiste


        // Controllo la disponibilità dell'articolo


        // Controllo, se il cart esiste aggiungo gli articoli, altrimenti lo creo
        return false;
    }

    @Override
    public boolean removeArticleToCart(int cartId, int articleId, int articleQta) {
        return false;
    }

    @Override
    public boolean createCart(int userId) throws UserNotFound {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(BigDecimal.valueOf(0)); // Dovrebbe essere di default 0.00, ma mi obbliga a settarlo
        cartRepository.save(cart);
        return true;
    }

    @Override
    public boolean clearCart(int cartId) {
        return false;
    }

    @Transactional
    @Override
    public boolean deleteCart(int cartId) throws CartNotFound {
        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
        cartRepository.deleteById(cartId);
        return true;
    }
}
