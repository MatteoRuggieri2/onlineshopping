package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService implements CartFunctions {

    @Autowired
    CartRepository cartRepository;

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
    public boolean clearCart(int cartId) {
        return false;
    }

    @Override
    public boolean deleteCart(int cartId) {
        cartRepository.deleteById(cartId);
        return true;
    }
}
