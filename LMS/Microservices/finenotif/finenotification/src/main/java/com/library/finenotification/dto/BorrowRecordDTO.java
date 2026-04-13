package com.library.finenotification.dto;

import java.time.LocalDate;

public class BorrowRecordDTO {

    private Long userId;
    private Long bookItemId;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookItemId() { return bookItemId; }
    public void setBookItemId(Long bookItemId) { this.bookItemId = bookItemId; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
