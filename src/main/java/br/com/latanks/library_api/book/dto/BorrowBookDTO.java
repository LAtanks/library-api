package br.com.latanks.library_api.book;

import java.time.LocalDateTime;

public record BorrowBookDTO(Long userId, LocalDateTime loanStart, LocalDateTime loanEnd) {
    
}
