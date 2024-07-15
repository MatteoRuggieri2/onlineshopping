package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.*;
import com.mr.onlineshopping.enums.OrderStatus;
import com.mr.onlineshopping.enums.PaymentType;
import com.mr.onlineshopping.exceptions.*;
import com.mr.onlineshopping.interfaces.*;
import com.mr.onlineshopping.repository.CartArticleRepository;
import com.mr.onlineshopping.repository.CartRepository;
import com.mr.onlineshopping.repository.OrderArticleRepository;
import com.mr.onlineshopping.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderFunctions {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserFunctions userService;

    @Autowired
    CartFunctions cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderArticleFunctions orderArticleService;

    @Autowired
    CartArticleRepository cartArticleRepository;

    @Autowired
    ArticleFunctions articleService;

    @Autowired
    OrderFunctions orderService;

    // Controlla se esiste un ordine - orderId
    @Override
    public boolean existById(int orderId) {
        return orderRepository.existsById(orderId);
    }

    // Ritorna un Optional dell'ordine - orderId
    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }

    // Ritorna una lista degli ordini di un utente - userId
    @Override
    public List<Order> getOrdersByUserId(int userId) throws UserNotFound {
        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
        return orderRepository.findByUserId(userId);
    }

    // Trasforma un carrello in un ordine (pagamento) - cartId, PaymentType
    //@Transactional
    @Override
    //TODO:
    // - Non viene decrementata la qta dell'articolo acquistato
    // - Non vengono eliminati gli articoli dal carrello
    // - Il carrello non viene eliminato
    public Order makeNewOrderByCartId(int cartId, PaymentType paymentType) throws CartNotFound, OrderNotCreated, ArticleNotFound, ArticleNotAvailable {
        Cart cart = cartService.getCartById(cartId).orElseThrow(() -> new CartNotFound(cartId));

        Order newOrder = new Order();
        newOrder.setUser(cart.getUser());
        newOrder.setTotalPrice(cart.getTotalPrice());
        newOrder.setPaymentType(paymentType);
        newOrder.setState(OrderStatus.CREATED);
        newOrder.setOrderDate(LocalDate.now());
        newOrder.setOrderTime(LocalTime.now());
        Order savedOrder = orderRepository.saveAndFlush(newOrder);
//
//        // Trasformo i cartArticles in orderArticles

//TODO     !IMPORTANTE: Questo aborto crea una PersistenceBag di HIBERNATE che fa casino con tutto, NON USARLA MAI!!!!
//        List<CartArticle> cartArticles = cart.getCartArticles();


        List<CartArticle> cartArticles = cartArticleRepository.findByCartId(cartId);
        List<OrderArticle> orderArticles = cartArticles.stream()
                .map(cartArticle -> {
                    try {
                        return this.transformCartArticleToOrderArticle(cartArticle, newOrder.getId());
                    } catch (ArticleNotFound | OrderNotFound e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();


        // Salvo tutti gli articoli nell'ordine (in order_article)
        orderArticleService.saveAllAndFlushOrderArticleList(orderArticles);


        // Scalo il numero di articoli acquistati
//        orderArticles.stream()
//                .map(orderArticle -> {
//                    try {
//                        return articleService.removeArticleQta(orderArticle.getOrderArticleID().getArticleId(), orderArticle.getQta());
//                    } catch (ArticleNotFound | ArticleNotAvailable e) {
//                        throw new RuntimeException(e);
//                    }
//                });

        for (OrderArticle orderArticle : orderArticles) {
            int articleId = orderArticle.getOrderArticleID().getArticleId();
            int articleQta = orderArticle.getQta();
            articleService.removeArticleQta(articleId, articleQta);
        }

        // Se l'ordine è stato creato con successo posso eliminare il carrello
        if (!this.existById(savedOrder.getId())) { throw new OrderNotCreated(); }
        cartService.deleteCart(cartId); // TODO: Se questo non lo elimina, cosa cabbo devo fare????


        return savedOrder;
    }


    @Override
    public OrderArticle transformCartArticleToOrderArticle(CartArticle cartArticle, int orderId) throws ArticleNotFound, OrderNotFound {
        int articleId = cartArticle.getCartArticleID().getArticleId();

        // Prendo articolo e ordine
        Article article = articleService.getArticleById(articleId).orElseThrow(() -> new ArticleNotFound(articleId));
        Order order = orderService.getOrderById(orderId).orElseThrow(() -> new OrderNotFound(orderId));

        // Creo nuovo OrderArticle
        OrderArticle orderArticle = new OrderArticle();
        OrderArticleID unifiedId = new OrderArticleID(orderId, articleId);
        orderArticle.setOrderArticleID(unifiedId);
        orderArticle.setQta(cartArticle.getQta());
        orderArticle.setOrder(order);
        orderArticle.setArticle(article);
        return orderArticle;
    }

    @Override
    public boolean saveOrderArticle(OrderArticle orderArticle) {
        return orderArticleService.saveNewOrderArticle(orderArticle);
    }

    // Imposta lo status di un'ordine
    @Override
    public boolean setOrderStatus(int orderId, OrderStatus orderStatus) throws OrderNotFound {
        if (!this.existById(orderId)) { throw new OrderNotFound(orderId); }
        int updatedRows = orderRepository.setStateByOrderId(orderId, orderStatus);
        return updatedRows > 0;
    }

}
