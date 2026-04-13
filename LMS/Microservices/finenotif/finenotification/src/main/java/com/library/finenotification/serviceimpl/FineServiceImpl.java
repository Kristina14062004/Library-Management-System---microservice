package com.library.finenotification.serviceimpl;

import com.library.finenotification.dto.*;
import com.library.finenotification.entity.Fine;
import com.library.finenotification.repository.FineRepository;
import com.library.finenotification.service.FineService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineServiceImpl implements FineService {

    private final FineRepository repo;

    public FineServiceImpl(FineRepository repo) {
        this.repo = repo;
    }

    @Override
    public FineDTO calculateFine(BorrowRecordDTO dto) {

        long daysLate = ChronoUnit.DAYS.between(
                dto.getDueDate(), dto.getReturnDate());

        if (daysLate <= 0) return null;

        Fine fine = new Fine();
        fine.setUserId(dto.getUserId());
        fine.setBookItemId(dto.getBookItemId());
        fine.setDaysLate((int) daysLate);
        fine.setAmount(daysLate * 10);
        fine.setStatus("UNPAID");

        repo.save(fine);

        FineDTO out = new FineDTO();
        out.setUserId(fine.getUserId());
        out.setBookItemId(fine.getBookItemId());
        out.setDaysLate(fine.getDaysLate());
        out.setAmount(fine.getAmount());
        out.setStatus(fine.getStatus());

        return out;
    }

    @Override
    public List<FineDTO> getFinesByUser(Long userId) {
        return repo.findByUserId(userId)
                .stream()
                .map(f -> {
                    FineDTO d = new FineDTO();
                    d.setUserId(f.getUserId());
                    d.setBookItemId(f.getBookItemId());
                    d.setDaysLate(f.getDaysLate());
                    d.setAmount(f.getAmount());
                    d.setStatus(f.getStatus());
                    return d;
                }).collect(Collectors.toList());
    }
}
