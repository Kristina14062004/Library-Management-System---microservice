package com.library.finenotification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.finenotification.entity.Fine;
import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findByUserId(Long userId);
}
