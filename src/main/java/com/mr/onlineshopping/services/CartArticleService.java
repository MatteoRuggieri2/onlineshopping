package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.entity.CartArticleID;
import com.mr.onlineshopping.exceptions.ArticleNotFoundInTheCart;
import com.mr.onlineshopping.exceptions.CartAlreadyExists;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.UserNotFound;
import com.mr.onlineshopping.interfaces.CartArticleFunctions;
import com.mr.onlineshopping.repository.CartArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartArticleService implements CartArticleFunctions {

    @Autowired
    CartArticleRepository cartArticleRepository;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Override
    public boolean existById(CartArticleID cartArticleID) {
        return cartArticleRepository.existsById(cartArticleID);
    }

    @Override
    public boolean existByArticleIdAndUserId(int articleId, int userId) throws UserNotFound, CartAlreadyExists {
        Cart userCart = cartService.getUserCart(userId);
        return true;
    }

    @Override
    public Optional<CartArticle> getCartArticleById(CartArticleID cartArticleID) {
        return cartArticleRepository.findById(cartArticleID);
    }

    // Questo metodo ritorna tutti gli articoli contenuti in un carrello esistente. (con cartId)
    @Override
    public List<CartArticle> getCartArticlesByCartId(int cartId) throws CartNotFound {
        if (!cartService.ifCartExist(cartId)) { throw new CartNotFound(cartId); }
        return cartArticleRepository.findByCartId(cartId);
    }

    // Questo metodo ritorna tutti gli articoli contenuti in un carrello esistente. (con userId)
    @Override
    public List<CartArticle> getCartArticlesByUserId(int userId) throws UserNotFound, CartNotFound {
        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
        Cart userCart = cartService.getCartById(userId)
                .orElseThrow(() -> new CartNotFound("The user's cart not found (cartId: " + userId + ")"));
        return this.getCartArticlesByCartId(userCart.getId());
    }

    // Questo metodo ritorna la qta di un articolo nel carrello
    @Override
    public int getCartArticleQta(CartArticleID cartArticleID) throws ArticleNotFoundInTheCart {
        CartArticle cartArticle = cartArticleRepository.findById(cartArticleID)
                .orElseThrow(() -> new ArticleNotFoundInTheCart(cartArticleID.getArticleId(), cartArticleID.getCartId()));
        return cartArticle.getQta();
    }

    @Override
    public boolean saveNewCartArticle(CartArticle cartArticle) {
        cartArticleRepository.saveAndFlush(cartArticle);
        return this.existById(cartArticle.getCartArticleID());
    }

    @Override
    public boolean deleteCartArticle(CartArticleID cartArticleID) {
        cartArticleRepository.deleteById(cartArticleID);
        return this.existById(cartArticleID);

    }

    @Override
    public int deleteAllCartArticles(int cartId) {
        int deletedRow = cartArticleRepository.deleteByCartId(cartId);
        cartArticleRepository.flush();
        return deletedRow;
    }
}
