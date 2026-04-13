package com.library.finenotification.service;

import com.library.finenotification.dto.*;
import java.util.List;

public interface FineService {

    FineDTO calculateFine(BorrowRecordDTO dto);
    List<FineDTO> getFinesByUser(Long userId);
}
