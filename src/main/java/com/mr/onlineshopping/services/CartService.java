package com.mr.onlineshopping.services;

import com.mr.onlineshopping.entity.*;
import com.mr.onlineshopping.exceptions.*;
import com.mr.onlineshopping.interfaces.ArticleFunctions;
import com.mr.onlineshopping.interfaces.CartArticleFunctions;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.repository.CartRepository;
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

    // Questo metodo controlla se un carrello esiste
    @Override
    public boolean ifCartExist(int cartId) {
        return cartRepository.existsById(cartId);
    }

    // Questo metodo ritorna un optional del cart cercato (con cartId)
    @Override
    public Optional<Cart> getCartById(int cartId) {
        return cartRepository.findById(cartId);
    }

    // Questo metodo ritorna un optional del cart cercato (con userId)
    @Override
    public Optional<Cart> getCartFromUserId(int userId) {
        return cartRepository.findByUserId(userId);
    }

    /* Questo metodo restituisce gli articoli di un carrello (cartId) */
    @Override
    public List<CartArticle> getCartArticles(int cartId) throws CartNotFound {
        Cart cart = this.getCartById(cartId).orElseThrow(() -> new CartNotFound(cartId));
        return cart.getCartArticles();
    }

    /* Questo metodo aggiunge un articolo nel carrello nella qta richiesta
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
    public boolean addArticleToUserCart(int userId, int articleId, int articleQta) throws UserNotFound, ArticleNotFound, ArticleNotAvailable, CartNotFound, CartAlreadyExists, InvalidSelectedArticleQta {
        if (articleQta <= 0) { throw new InvalidSelectedArticleQta(articleId, articleQta); }
        if (!userService.ifUserExists(userId)) {throw new UserNotFound(userId); }
        if (!articleService.existsById(articleId)) {throw new ArticleNotFound(articleId); }
        if (!articleService.checkAvailability(articleId, articleQta)) { throw new ArticleNotAvailable(articleId, articleQta); }
        Cart cart = this.getUserCart(userId);
        this.addArticleToCart(cart.getId(), articleId, articleQta);
        return true;
    }

    /* Questo metodo serve a modificare la qta di un determinato articolo già all'interno
    * del carrello. Se la qta non è disponibile lancia un'exception.
    * Se la qta dell'articolo è 0 elimina l'articolo dal carrello. */
    // Se qta > oldQta throw new ToFewItemInTheCart()
    // Se qta = oldQta elimino il cart article
    // Se qta < oldQta modifico la qta
    @Transactional
    @Override
    public boolean editArticleQtaToCart(int userId, int articleId, int articleQta) throws UserNotFound, ArticleNotFound, ArticleNotAvailable, CartNotFound, ArticleNotFoundInTheCart {
        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
        if (!articleService.existsById(articleId)) { throw new ArticleNotFound(articleId); }
        if (!articleService.checkAvailability(articleId, articleQta)) { throw new ArticleNotAvailable(articleId, articleQta); }
        Cart userCart = cartRepository.findByUserId(userId).orElseThrow(() -> new CartNotFound("The user's cart was not found"));

        // Controllo che l'articolo sia dentro il carrello - Prendo la riga con l'id unificato
        CartArticle cartArticle = cartArticleService.getCartArticleById(new CartArticleID(userCart.getId(), articleId))
                .orElseThrow(() -> new ArticleNotFoundInTheCart(articleId, userCart.getId()));

        // Se la qta è ZERO elimino quell'articolo e calcolo il prezzo totale del carrello.
        if (articleQta == 0) {
            this.deleteArticleToCart(userCart.getId(), articleId);
//            this.updateCartTotalPrice(userCart.getId());
            return true;
        }

//        // metodo 1: Modifico la qta dell'articolo nel carrello
        /* TODO ERROR -> Non aggiorna in tempo la qta perchè il commit della transaction non viene eseguito immediatamente,
            ma viene eseguito quando la transazione è terminata. Per questo il passaggio successivo si trova ancora
            il valore non aggiornato, perchè viene aggiornato alla fine. */
//        cartArticleRepository.updateCartArticleQtaToCart(userCart.getId(), articleId, articleQta);
//        cartArticleRepository.flush();
        // dovrei effettuare a mano un commit qui


        // metodo 2: Risalvo tutto il cartArticle
        //TODO -> Salvando tutta la riga la salva in tempo (il save() comprende il commit immediato)
        cartArticle.setQta(articleQta);
        cartArticleService.saveNewCartArticle(cartArticle);



        //TODO |Error  -  Se faccio UPDATE, Quando arrivo qua la qta non è stata ancora aggiornata (l'update non ha il commit immediato)
        this.updateCartTotalPrice(userCart.getId());
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
        if (!articleService.existsById(articleId)) { throw new ArticleNotFound(articleId); }
        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
        CartArticleID unifiedId = new CartArticleID(cartId, articleId);
        cartArticleService.deleteCartArticle(unifiedId);
        this.updateCartTotalPrice(cartId);
        return true;
    }

    // TODO: Vedi se ti conviene passare un Cart o un CartDTO (in base alla struttura che avrai nel backend)
    //@Override
    public boolean updateCart(Cart cart) {
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
    public Cart createCart(int userId) throws UserNotFound, CartAlreadyExists {
        User user = userService.getUserById(userId).orElseThrow(() -> new UserNotFound(userId));
        Optional<Cart> userCart = this.getCartFromUserId(userId);
        if (userCart.isPresent()) {
            throw new CartAlreadyExists("The user's cart (userId: " + userId + ") already exists.");
        }
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public boolean clearCart(int cartId) throws CartNotFound {
        Cart cart = cartRepository.findById(cartId).get();
        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
        cartArticleService.deleteAllCartArticles(cartId);
        return true;
    }

    @Transactional
    @Override
    public boolean deleteCart(int cartId) throws CartNotFound {
        if (!cartRepository.existsById(cartId)) { throw new CartNotFound(cartId); }
        this.clearCart(cartId); // Svuoto il carrello
        cartRepository.deleteById(cartId);
        return !cartRepository.existsById(cartId);
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
        List<CartArticle> cartArticles = cartArticleService.getCartArticlesByCartId(cartId);

        /* Trasformo la lista di CartArticle in stream, poi con map per ogni articolo
        mi prendo la somma unitaria, dopo che ho tutte le somme unitarie uso il reduce
        per sommarle e avere il totale. */
        totalPrice = cartArticles.stream()
                .map(cartArticle -> sumSameCartArticle(cartArticle)) // [100, 200, 300]
                .reduce(BigDecimal.valueOf(0), (a, b) -> a.add(b)); // 100 + 200 + 300 = 600

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
        Article article = articleService.getArticleById(articleId).orElseThrow(() -> new ArticleNotFound(articleId));
        if (!articleService.checkAvailability(articleId, articleQta)) { throw new ArticleNotAvailable(articleId, articleQta); }
        Cart cart = this.getCartById(cartId).orElseThrow(() -> new CartNotFound(cartId));
        CartArticle newCartArticle = new CartArticle();
        newCartArticle.setCartArticleID(new CartArticleID(cartId, articleId));
        newCartArticle.setQta(articleQta);
        newCartArticle.setCart(cart);
        newCartArticle.setArticle(article);
        cartArticleService.saveNewCartArticle(newCartArticle);

        // Modifico il totale del cart
        this.updateCartTotalPrice(cartId);
        return true;
    }

    /* Questo metodo ritorna il carrello dell'utente.
    Se il carrello dell'utente non esiste lo crea */
    @Transactional
    public Cart getUserCart(int userId) throws UserNotFound, CartAlreadyExists {
        User user = userService.getUserById(userId).orElseThrow(() -> new UserNotFound(userId));
        Optional<Cart> userCart = Optional.ofNullable(user.getCart());
        if (userCart.isEmpty()) {
            return this.createCart(userId);
        }
        return userCart.get();
    }

    /* Questo metodo calcola il prezzo totale di un articolo nel carrello,
    moltiplicando la qta per il prezzo unitario. */
    public BigDecimal sumSameCartArticle(CartArticle cartArticle) {
        int articleId = cartArticle.getCartArticleID().getArticleId();
        BigDecimal articleUnitPrice = articleService.getArticleById(articleId).get().getPrice();
        BigDecimal articleQta = BigDecimal.valueOf(cartArticle.getQta());
        return articleUnitPrice.multiply(articleQta);
    }
}
