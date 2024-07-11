package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.*;
import com.mr.onlineshopping.exceptions.*;
import com.mr.onlineshopping.interfaces.ArticleFunctions;
import com.mr.onlineshopping.interfaces.CartArticleFunctions;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.repository.ArticleRepository;
import com.mr.onlineshopping.repository.CartArticleRepository;
import com.mr.onlineshopping.repository.CartRepository;
import com.mr.onlineshopping.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartFunctions {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartArticleFunctions cartArticleService;

    @Autowired
    UserFunctions userService;

    @Autowired
    ArticleFunctions articleService;

    @Override
    public boolean ifCartExist(int cartId) {
        return cartRepository.existsById(cartId);
    }

    @Override
    public Optional<Cart> getCartById(int cartId) {
        return cartRepository.findById(cartId);
    }

    // TODO -> Da inserire nell'UserService (in base all'URL)
    @Override
    public Optional<Cart> getCartFromUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    /* Questo metodo restituisce gli articoli di un carrello */
    @Override
    public List<CartArticle> getCartArticles(int cartId) throws CartNotFound {
        Cart cart = this.getCartById(cartId).orElseThrow(() -> new CartNotFound(cartId));
//        return cart.getArticles();
        return List.of();
    }

    /* Questo metodo aggiunge 1 articolo per volta nella qta richiesta
    *
    * Exceptions:
    * - ArticleNotFound -> Se l'articolo richiesto non esiste
    * - ArticleNotAvailable -> Se l'articolo non è disponibile nella qta richiesta
    * - CartNotFound -> Se il carrello non esiste
    *
    * Return:
    * TRUE se l'articolo viene aggiunto correttamente al Cart */
    @Transactional
    @Override
    public boolean addArticleToUserCart(int userId, int articleId, int articleQta) throws UserNotFound, ArticleNotFound, ArticleNotAvailable, CartNotFound, CartAlreadyExists {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
//        if (!articleRepository.existsById(articleId)) {throw new ArticleNotFound(articleId); }
//        if (!articleService.checkAvailability(articleId, articleQta)) { throw new ArticleNotAvailable(articleId, articleQta); }
//        Cart cart = this.getUserCart(userId);
//        this.addArticleToCart(cart.getId(), articleId, articleQta);
        return true;
    }


    // Se qta > oldQta throw new ToFewItemInTheCart()
    // Se qta = oldQta elimino il cart article
    // Se qta < oldQta modifico la qta
    @Transactional
    @Override
    public boolean editArticleQtaToCart(int userId, int articleId, int articleQta) throws ArticleNotFound, ArticleNotFoundInTheCart, CartNotFound, ToFewItemInTheCart, UserNotFound, ArticleNotAvailable {
//        if (!userRepository.existsById(userId)) { throw new UserNotFound(userId); }
//        if (!articleRepository.existsById(articleId)) { throw new ArticleNotFound(articleId); }
//        if (!articleService.checkAvailability(articleId, articleQta)) { throw new ArticleNotAvailable(articleId, articleQta); }
//        Cart userCart = cartRepository.findByUserId(userId).orElseThrow(() -> new CartNotFound("The user's cart was not found"));
//
//        // Controllo che l'articolo sia dentro il carrello - Prendo la riga con l'id unificato
//        CartArticle cartArticle = cartArticleRepository.findById(new CartArticleID(userCart.getId(), articleId))
//                .orElseThrow(() -> new ArticleNotFoundInTheCart(articleId, userCart.getId()));
//
//        // Se la qta è ZERO elimino quell'articolo e calcolo il prezzo totale del carrello.
//        if (articleQta == 0) {
//            this.deleteArticleToCart(userCart.getId(), articleId);
//            this.updateCartTotalPrice(userCart.getId());
//            return true;
//        }
//
////        // metodo 1: Modifico la qta dell'articolo nel carrello
//        /* TODO ERROR -> Non aggiorna in tempo la qta perchè il commit della transaction non viene eseguito immediatamente,
//            ma viene eseguito quando la transazione è terminata. Per questo il passaggio successivo si trova ancora
//            il valore non aggiornato, perchè viene aggiornato alla fine. */
////        cartArticleRepository.updateCartArticleQtaToCart(userCart.getId(), articleId, articleQta);
////        cartArticleRepository.flush();
//        // dovrei effettuare a mano un commit qui
//
//
//        // metodo 2: Risalvo tutto il cartArticle
//        //TODO -> Salvando tutta la riga la salva in tempo (il save() comprende il commit immediato)
//        cartArticle.setQta(articleQta);
//        cartArticleRepository.save(cartArticle);
//
//
//
//        //TODO |Error  -  Quando arrivo qua la qta non è stata aggiornata se faccio UPDATE
//        this.updateCartTotalPrice(userCart.getId());
        return true;
    }

    /* Questo metodo elimina un articolo da un carrello.
    *
    * Params:
    * int cartId -> Id del carrello
    * int articleId -> Id dell'articolo
    *
    * Returnments:
    * boolean true
    *
    * Exceptions:
    * ArticleNotFound
    * CartNotFound */
    @Transactional
    @Override
    public boolean deleteArticleToCart(int cartId, int articleId) throws ArticleNotFound, CartNotFound {
//        if (!articleRepository.existsById(articleId)) { throw new ArticleNotFound(articleId); }
//        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
//        CartArticleID unifiedId = new CartArticleID(cartId, articleId);
//        cartArticleRepository.deleteById(unifiedId);
        return true;
    }

    @Override
    public boolean updateCart(Cart cart) throws CartNotFound, UserNotFound, ArticleNotFound {
//        // Controllo che esista il cart
//        if (!cartRepository.existsById(cart.getId())) { throw new CartNotFound(cart.getId()); }
//        // Controllo che esista l'user associato al carrello
//        if (!userRepository.existsById(cart.getUser().getId())) { throw new UserNotFound(cart.getUser().getId()); }
//        // Controllo che esistano tutti gli articoli e che la qta richiesta sia disponibile
//        List<Article> articles = cart.getArticles();
//        for (int i = 0; i < articles.size(); i++) {
//            Article article = articles.get(i);
//            int articleId = article.getId();
//            int articleQta = article.get
//            if (!articleRepository.existsById(articleId)) { throw new ArticleNotFound(articleId); }
//            if (!articleService.checkAvailability(articleId, )) { throw new ArticleNotFound(articleId); }
//        }
//
//
//        // Aggiorno il carrello
        return false;
    }

    /* Questo metodo accetta come argomento l'id dell'utente.
    * Se l'utente non viene trovato lancia "UserNotFound Exception".
    * Se non è presente un carrello collegato all'utente, lo crea.
    * Return TRUE se crea un carrello
    * Return FALSE se non lo crea (magari già esiste) */
    @Transactional
    @Override
    public boolean createCart(int userId) throws UserNotFound, CartAlreadyExists {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
//        Optional<Cart> userCart = this.getCartFromUserId(userId);
//        if (userCart.isPresent()) {
//            throw new CartAlreadyExists("The user's cart (userId: " + userId + ") already exists.");
//        }
//        Cart cart = new Cart();
//        cart.setUser(user);
//        cartRepository.save(cart);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteCart(int cartId) throws CartNotFound {
        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
        cartRepository.deleteById(cartId);
        return true;
    }

    @Override
    public boolean updateCartTotalPrice(int cartId) throws CartNotFound {
        BigDecimal cartTotalPrice = this.calculateCartTotalPrice(cartId);
        cartRepository.updateCartTotalPrice(cartId, cartTotalPrice);
        return true;
    }

    public BigDecimal calculateCartTotalPrice(int cartId) throws CartNotFound {
        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
        BigDecimal totalPrice = BigDecimal.ZERO;
//        List<CartArticle> cartArticles = cartArticleRepository.findByCartId(cartId);

//        totalPrice = cartArticles.stream()
//                .map(cartArticle -> sumSameCartArticle(cartArticle)) // [100, 200, 300]
//                .reduce(BigDecimal.valueOf(0), (a, b) -> a.add(b)); // 100 + 200 + 300 = 600

/*      // VARIANTE
        totalPrice = cartArticles.stream()
                .map(this::sumSameCartArticle)
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
*/
        return totalPrice;
    }

    /* Questo metodo ha il compito di inserire gli articoli in un carrello già definito.
    * Params: int cartId, int articleId, int articleQta
    * Exceptions: ArticleNotFound, ArticleNotAvailable, CartNotFound
    * Returnments: boolean */
    public boolean addArticleToCart(int cartId, int articleId, int articleQta) throws ArticleNotFound, ArticleNotAvailable, CartNotFound {
//        if (!articleRepository.existsById(articleId)) { throw new ArticleNotFound(articleId); }
//        if (!articleService.checkAvailability(articleId, articleQta)) { throw new ArticleNotAvailable(articleId, articleQta); }
//        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
//        CartArticle newCartArticle = new CartArticle();
//        newCartArticle.setCartArticleID(new CartArticleID(cartId, articleId));
//        newCartArticle.setQta(articleQta);
//        cartArticleRepository.save(newCartArticle);

        // Modifico il totale del cart
        this.updateCartTotalPrice(cartId);
        return true;
    }

    /* Questo metodo ritorna il carrello dell'utente.
    Se il carrello dell'utente non esiste lo crea */
    @Transactional
    public Cart getUserCart(int userId) throws UserNotFound, CartAlreadyExists {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
//        Optional<Cart> userCart = Optional.ofNullable(user.getCart());
//        if (userCart.isEmpty()) {
//            this.createCart(userId);
//            userCart = cartRepository.findByUserId(userId);
//        }
//        return userCart.get();
        return null;
    }

    /* Questo metodo calcola il prezzo totale di un articolo nel carrello,
    moltiplicando la qta per il prezzo unitario. */
    public BigDecimal sumSameCartArticle(CartArticle cartArticle) {
//        int articleId = cartArticle.getCartArticleID().getArticleId();
//        Article article = articleRepository.findById(articleId).get();
//        BigDecimal articleUnitPrice = article.getPrice();
//        BigDecimal articleQta = BigDecimal.valueOf(cartArticle.getQta());
//        return articleUnitPrice.multiply(articleQta);
        return BigDecimal.ZERO;
    }
}
