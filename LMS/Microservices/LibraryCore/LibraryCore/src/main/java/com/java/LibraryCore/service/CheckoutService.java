package com.java.LibraryCore.service;

import com.java.LibraryCore.dto.CheckoutRequestDTO;

public interface CheckoutService {
    String checkoutBook(CheckoutRequestDTO request);
}
