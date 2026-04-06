package br.com.latanks.library_api.book.dto;

import java.time.LocalDateTime;

public record BorrowBookDTO(Long userId, LocalDateTime loanStart, LocalDateTime loanEnd) {
    
}
