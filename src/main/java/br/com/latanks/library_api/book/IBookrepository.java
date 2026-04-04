package br.com.latanks.library_api.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBookrepository extends JpaRepository<Book, Long> {

    //@Query("SELECT u FROM tb_books u WHERE u.name = :name")
    List<Book> findAllByTitleContainingIgnoreCase(String title);
}
