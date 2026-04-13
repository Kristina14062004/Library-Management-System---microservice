package com.lms.bookcatlog.controller;

import com.lms.bookcatlog.entity.BookItem;
import com.lms.bookcatlog.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookitems")
public class BookItemController {

    @Autowired
    private BookItemService bookItemService;

    @GetMapping("/getALLBookItems")
    public List<BookItem> getAllBookItems() {
        return bookItemService.getAllBookItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookItem> getBookItemById(@PathVariable Long id) {
        BookItem bookItem = bookItemService.getBookItemById(id);
        if (bookItem != null) {
            return ResponseEntity.ok(bookItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addBookItem")
    public ResponseEntity<BookItem> addBookItem(@RequestBody BookItem bookItem) {
        BookItem saved = bookItemService.saveBookItem(bookItem);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookItem(@PathVariable Long id) {
        bookItemService.deleteBookItem(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byBook/{bookId}")
    public List<BookItem> getByBookId(@PathVariable Long bookId) {
        return bookItemService.getBookItemsByBookId(bookId);
    }

    @GetMapping("/status/{status}")
    public List<BookItem> getByStatus(@PathVariable String status) {
        return bookItemService.getBookItemsByStatus(status);
    }
}