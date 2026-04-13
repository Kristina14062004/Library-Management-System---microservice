package com.library.finenotification.controller;

import com.library.finenotification.dto.*;
import com.library.finenotification.service.FineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    private final FineService service;

    public FineController(FineService service) {
        this.service = service;
    }

    @PostMapping("/calculate")
    public FineDTO calculate(@RequestBody BorrowRecordDTO dto) {
        return service.calculateFine(dto);
    }

    @GetMapping("/user/{userId}")
    public List<FineDTO> getByUser(@PathVariable Long userId) {
        return service.getFinesByUser(userId);
    }
}
