package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.dto.ArticleDTO;
import com.mr.onlineshopping.dto.CartArticleDTO;
import com.mr.onlineshopping.dto.CartDTO;
import com.mr.onlineshopping.entity.Article;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.CartArticle;
import com.mr.onlineshopping.exceptions.*;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.response.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    // GET Cart by cart_id
    @GetMapping("carts/{cartId}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartById(@PathVariable("cartId") int cartId) throws CartNotFound {
        Cart cart = cartService.getCartById(cartId)
                .orElseThrow(() -> new CartNotFound(cartId));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(cartDTO, "Success", HttpStatus.OK.value()));
    }

    // GET Cart from user_id
    @GetMapping("carts/user/{userId}")
    public ResponseEntity<ApiResponse<CartDTO>> getCartFromUserId(@PathVariable("userId") int userId) throws UserNotFound, CartNotFound {
        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
        Cart cart = cartService.getCartFromUserId(userId)
                .orElseThrow(() -> new CartNotFound("The user does not have a cart"));

        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(cartDTO, "Success", HttpStatus.OK.value()));
    }

    // GET Cart articles
    @GetMapping("carts/{cartId}/articles")
    public ResponseEntity<ApiResponse<List<CartArticleDTO>>> getCartArticles(@PathVariable("cartId") int cartId) throws CartNotFound {
        List<CartArticle> cartArticles = cartService.getCartArticles(cartId);

        // Per ogni articolo del set lo devo mappare in ArticleDTO e salvarlo in un nuovo set
        List<CartArticleDTO> cartArticlesDTO = cartArticles.stream()
                .map(article -> modelMapper.map(article, CartArticleDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(cartArticlesDTO, "Success", HttpStatus.OK.value()));

    }

    // ADD Article to Cart
    @PostMapping("carts/user/{userId}/{articleId}/{articleQta}")
    public ResponseEntity<ApiResponse<Boolean>> addArticleToCart(@PathVariable("userId") int userId,
                                                    @PathVariable("articleId") int articleId,
                                                    @PathVariable("articleQta") int articleQta) throws ArticleNotFound, ArticleNotAvailable, UserNotFound, CartNotFound, CartAlreadyExists, InvalidSelectedArticleQta {
        cartService.addArticleToUserCart(userId, articleId, articleQta);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", HttpStatus.OK.value()));
    }

    // EDIT Article to Cart
    @PatchMapping("carts/user/{userId}/{articleId}/{articleQta}")
    public ResponseEntity<ApiResponse<Boolean>> editArticleQtaToCart(@PathVariable("userId") int userId,
                                                        @PathVariable("articleId") int articleId,
                                                        @PathVariable("articleQta") int articleQta) throws CartNotFound, ArticleNotFound, ArticleNotAvailable, UserNotFound, ArticleNotFoundInTheCart {
        cartService.editArticleQtaToCart(userId, articleId, articleQta);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", HttpStatus.OK.value()));

    }

    // DELETE Article to Cart
    @DeleteMapping("carts/{cartId}/{articleId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteArticleQtaToCart(@PathVariable("cartId") int cartId,
                                                                       @PathVariable("articleId") int articleId) throws CartNotFound, ArticleNotFound {
        cartService.deleteArticleToCart(cartId, articleId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", HttpStatus.OK.value()));

    }

    // CREATE Cart
    @PostMapping("carts/{userId}")
    public ResponseEntity<ApiResponse<CartDTO>> createCart(@PathVariable("userId") int userId) throws UserNotFound, CartAlreadyExists {
        Cart cart = cartService.createCart(userId);
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(cartDTO, "Success", HttpStatus.OK.value()));
    }

    // DELETE Cart
    @DeleteMapping("carts/{cartId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCartById(@PathVariable("cartId") int cartId) throws CartNotFound {
        boolean deleteStatus = cartService.deleteCart(cartId);
        return deleteStatus
                ? ResponseEntity.ok(new ApiResponse<>(true, "Success", HttpStatus.OK.value()))
                : new ResponseEntity<>(new ApiResponse<>(false, "Fail", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

}
