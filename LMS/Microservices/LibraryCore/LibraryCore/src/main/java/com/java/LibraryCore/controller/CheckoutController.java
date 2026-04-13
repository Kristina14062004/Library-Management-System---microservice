package com.java.LibraryCore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.LibraryCore.dto.CheckoutRequestDTO;
import com.java.LibraryCore.service.CheckoutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
 
    @Autowired
    private CheckoutService checkoutService;
 
    @PostMapping
    public ResponseEntity<String> checkout(@RequestBody CheckoutRequestDTO request) {
        String result = checkoutService.checkoutBook(request);
        if (result.equals("Checkout successful.")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
 