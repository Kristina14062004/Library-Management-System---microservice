package com.library.finenotification.dto;

public class FineDTO {

    private Long userId;
    private Long bookItemId;
    private int daysLate;
    private double amount;
    private String status;

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getBookItemId() { return bookItemId; }
    public void setBookItemId(Long bookItemId) { this.bookItemId = bookItemId; }

    public int getDaysLate() { return daysLate; }
    public void setDaysLate(int daysLate) { this.daysLate = daysLate; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
