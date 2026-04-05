package br.com.latanks.library_api.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBookrepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByTitleContainingIgnoreCase(String title);
}
