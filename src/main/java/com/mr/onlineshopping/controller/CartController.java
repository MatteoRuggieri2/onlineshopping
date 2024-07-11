package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.dto.ArticleDTO;
import com.mr.onlineshopping.dto.CartDTO;
import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.exceptions.*;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CartController {

    // Autowired del mapper per i DTO
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartFunctions cartService;

    @Autowired
    private UserFunctions userService;

    // TODO - OK
    // GET Cart by cart_id
    @GetMapping("carts/{cartId}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartById(@PathVariable("cartId") int cartId) throws CartNotFound {
        Cart cart = cartService.getCartById(cartId)
                .orElseThrow(() -> new CartNotFound(cartId));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(cartDTO, "Success", HttpStatus.OK.value()));
    }

    // GET Cart from user_id
//    @GetMapping("carts/user/{userId}")
//    public ResponseEntity<ApiResponse<CartDTO>> getCartFromUserId(@PathVariable("userId") int userId) throws UserNotFound, CartNotFound {
//        if (!userService.ifUserExist(userId)) { throw new UserNotFound(userId); }
//        Cart cart = cartService.getCartFromUserId(userId)
//                .orElseThrow(() -> new CartNotFound("The user does not have a cart"));
//
//        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
//        return ResponseEntity.ok(cartDTO);
//    }

    // GET Cart articles
//    @GetMapping("carts/{cartId}/articles")
//    public ResponseEntity<Set<ArticleDTO>> getCartArticles(@PathVariable("cartId") int cartId) throws CartNotFound {
//        Set<Article> cartArticles = cartService.getCartArticles(cartId);
//
//        // Per ogni articolo del set lo devo mappare in ArticleDTO e salvarlo in un nuovo set
//        Set<ArticleDTO> cartArticlesDTO = cartArticles.stream()
//                .map(article -> modelMapper.map(article, ArticleDTO.class))
//                .collect(Collectors.toSet());
//
//        return ResponseEntity.ok(cartArticlesDTO);
//    }

    // ADD Article to Cart
    @PostMapping("users/{userId}/cart/insert/{articleId}/{articleQta}")
    public ResponseEntity<Boolean> addArticleToCart(@PathVariable("userId") int userId,
                                                    @PathVariable("articleId") int articleId,
                                                    @PathVariable("articleQta") int articleQta) throws ArticleNotFound, ArticleNotAvailable, UserNotFound, CartNotFound, CartAlreadyExists {
        cartService.addArticleToUserCart(userId, articleId, articleQta);
        return ResponseEntity.ok(true);
    }

    // ADD Article to Cart
    @PatchMapping("carts/{userId}/alter/{articleId}/{articleQta}")
    public ResponseEntity<Boolean> editArticleQtaToCart(@PathVariable("userId") int userId,
                                                        @PathVariable("articleId") int articleId,
                                                        @PathVariable("articleQta") int articleQta) throws CartNotFound, ArticleNotFound, ToFewItemInTheCart, ArticleNotAvailable, UserNotFound, ArticleNotFoundInTheCart {
        cartService.editArticleQtaToCart(userId, articleId, articleQta);
        return ResponseEntity.ok(true);
    }

    // CREATE Cart
    @PostMapping("carts/create/{userId}")
    public ResponseEntity<Boolean> createCart(@PathVariable("userId") int userId) throws UserNotFound, CartAlreadyExists {
        return ResponseEntity.ok(cartService.createCart(userId));
    }

    // DELETE Cart
    @DeleteMapping("carts/{cartId}/delete")
    public ResponseEntity<Boolean> deleteCartById(@PathVariable("cartId") int cartId) throws CartNotFound {
        cartService.deleteCart(cartId);
        return ResponseEntity.ok(true);
    }

    // TODO - test
//    @GetMapping("carts/{cartId}/totalprice")
//    public ResponseEntity<Boolean> totalPrice(@PathVariable("cartId") int cartId) throws CartNotFound {
//        cartService.calculateCartTotalPrice(cartId);
//
//        return ResponseEntity.ok(true);
//    }
}
