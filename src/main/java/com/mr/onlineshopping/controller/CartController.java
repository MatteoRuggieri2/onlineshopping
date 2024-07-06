package com.mr.onlineshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {

    @GetMapping("test/{string}")
    public ResponseEntity<?> viewCartForClient(@PathVariable("string") String string ) {
        return ResponseEntity.ok(string);

    }
}
