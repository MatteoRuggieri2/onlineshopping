package com.mr.onlineshopping.controller;

import com.mr.onlineshopping.dto.CartDTO;
import com.mr.onlineshopping.dto.UserDTO;
import com.mr.onlineshopping.entity.Cart;
import com.mr.onlineshopping.entity.User;
import com.mr.onlineshopping.exceptions.CartNotFound;
import com.mr.onlineshopping.exceptions.LoginWrongEmailOrPassword;
import com.mr.onlineshopping.exceptions.UserNotFound;
import com.mr.onlineshopping.interfaces.CartFunctions;
import com.mr.onlineshopping.interfaces.UserFunctions;
import com.mr.onlineshopping.response.ApiResponse;
import com.mr.onlineshopping.utils.ObjectMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class UserController {

    // Autowired del mapper per i DTO
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserFunctions userService;

    @Autowired
    private CartFunctions cartService;


    // GET User by userId
    @GetMapping("users/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable("userId") int userId) throws UserNotFound {
        User user = userService.getUserById(userId).orElseThrow(() -> new UserNotFound(userId));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(userDTO, "Success", HttpStatus.OK.value()));
    }

    // GET User by email
    @GetMapping("users/email")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(@RequestParam("email") String email) throws UserNotFound {
        User user = userService.getUserByEmail(email).orElseThrow(() -> new UserNotFound(email));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(new ApiResponse<>(userDTO, "Success", HttpStatus.OK.value()));
    }

    // GET User by email
    @GetMapping("login")
    public ResponseEntity<ApiResponse<Boolean>> login(@RequestParam("email") String email,
                                                      @RequestParam("password") String password) throws LoginWrongEmailOrPassword {
        boolean loginStatus = userService.login(email, password);
        if (!loginStatus) { throw new LoginWrongEmailOrPassword(); }
        return ResponseEntity.ok(new ApiResponse<>(true, "Logged", HttpStatus.OK.value()));
    }

    // GET Cart from user_id
    @GetMapping("users/{userId}/cart")
    public ResponseEntity<CartDTO> getCartFromUserId(@PathVariable("userId") int userId) throws UserNotFound, CartNotFound {
        if (!userService.ifUserExists(userId)) { throw new UserNotFound(userId); }
        Cart cart = cartService.getCartFromUserId(userId).orElseThrow(() -> new CartNotFound("The user does not have a cart"));
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class); // Setto tutti gli attributi del DTO con il mapper
        return ResponseEntity.ok(cartDTO);
    }

}
