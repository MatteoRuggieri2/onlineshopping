package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.*;
import com.mr.onlineshopping.exceptions.ArticleNotFoundInTheOrder;
import com.mr.onlineshopping.exceptions.OrderNotFound;
import com.mr.onlineshopping.interfaces.OrderArticleFunctions;
import com.mr.onlineshopping.interfaces.OrderFunctions;
import com.mr.onlineshopping.repository.OrderArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderArticleService implements OrderArticleFunctions {

    @Autowired
    OrderArticleRepository orderArticleRepository;

    @Autowired
    OrderFunctions orderService;

    @Override
    public boolean existById(OrderArticleID orderArticleID) {
        return orderArticleRepository.existsById(orderArticleID);
    }

    @Override
    public List<OrderArticle> getOrderArticlesByOrderId(int orderId) throws OrderNotFound {
        if (!orderService.existById(orderId)) { throw new OrderNotFound(orderId); }
        return orderArticleRepository.findByOrderId(orderId);
    }

    @Override
    public int getOrderArticleQta(OrderArticleID orderArticleID) throws ArticleNotFoundInTheOrder {
        OrderArticle orderArticle = orderArticleRepository.findById(orderArticleID)
                .orElseThrow(() -> new ArticleNotFoundInTheOrder(orderArticleID.getArticleId(), orderArticleID.getOrderId()));
        return orderArticle.getQta();
    }

    @Override
    public boolean saveNewOrderArticle(OrderArticle orderArticle) {
        orderArticleRepository.saveAndFlush(orderArticle);
        return this.existById(orderArticle.getOrderArticleID());
    }

    @Override
    public void saveAllAndFlushOrderArticleList(List<OrderArticle> orderArticles) {
        orderArticleRepository.saveAll(orderArticles);
    }







//    @Override
//    public boolean existById(CartArticleID cartArticleID) {
//        return cartArticleRepository.existsById(cartArticleID);
//    }
//
//    @Override
//    public boolean existByArticleIdAndUserId(int articleId, int userId) throws UserNotFound, CartAlreadyExists {
//        Cart userCart = cartService.getUserCart(userId);
//        return true;
//    }
//
//    @Override
//    public Optional<CartArticle> getCartArticleById(CartArticleID cartArticleID) {
//        return cartArticleRepository.findById(cartArticleID);
//    }
//
//    // Questo metodo ritorna tutti gli articoli contenuti in un carrello esistente. (con cartId)
//    @Override
//    public List<CartArticle> getCartArticlesByCartId(int cartId) throws CartNotFound {
//        if (!cartService.ifCartExist(cartId)) { throw new CartNotFound(cartId); }
//        return cartArticleRepository.findByCartId(cartId);
//    }
//
//    // Questo metodo ritorna tutti gli articoli contenuti in un carrello esistente. (con userId)
//    @Override
//    public List<CartArticle> getCartArticlesByUserId(int userId) throws UserNotFound, CartNotFound {
//        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
//        Cart userCart = cartService.getCartById(userId)
//                .orElseThrow(() -> new CartNotFound("The user's cart not found (cartId: " + userId + ")"));
//        return this.getCartArticlesByCartId(userCart.getId());
//    }
//
//    // Questo metodo ritorna la qta di un articolo nel carrello
//    @Override
//    public int getCartArticleQta(CartArticleID cartArticleID) throws ArticleNotFoundInTheCart {
//        CartArticle cartArticle = cartArticleRepository.findById(cartArticleID)
//                .orElseThrow(() -> new ArticleNotFoundInTheCart(cartArticleID.getArticleId(), cartArticleID.getCartId()));
//        return cartArticle.getQta();
//    }
//
//    @Override
//    public boolean saveNewCartArticle(CartArticle cartArticle) {
//        cartArticleRepository.saveAndFlush(cartArticle);
//        return this.existById(cartArticle.getCartArticleID());
//    }
//
//    @Override
//    public boolean deleteCartArticle(CartArticleID cartArticleID) {
//        cartArticleRepository.deleteById(cartArticleID);
//        return this.existById(cartArticleID);
//
//    }
//
//    @Override
//    public int deleteAllCartArticles(int cartId) {
//        int deletedRow = cartArticleRepository.deleteByCartId(cartId);
//        cartArticleRepository.flush();
//        return deletedRow;
//    }

}
