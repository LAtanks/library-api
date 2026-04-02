package br.com.latanks.library_api.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookrepository extends JpaRepository<Book, Long> {
    
}
