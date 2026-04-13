package com.lms.bookcatlog.service;

import com.lms.bookcatlog.entity.BookItem;
import java.util.List;

public interface BookItemService {
    List<BookItem> getAllBookItems();
    BookItem getBookItemById(Long id);
    BookItem saveBookItem(BookItem bookItem);
    void deleteBookItem(Long id);
    List<BookItem> getBookItemsByBookId(Long bookId);
    List<BookItem> getBookItemsByStatus(String status);
}