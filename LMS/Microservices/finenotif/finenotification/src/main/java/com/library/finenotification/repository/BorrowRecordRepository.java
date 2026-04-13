package com.library.finenotification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.finenotification.entity.BorrowRecord;

public interface BorrowRecordRepository
        extends JpaRepository<BorrowRecord, Long> {
}
