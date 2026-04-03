package br.com.latanks.library_api.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.latanks.library_api.Utils.Utils;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private IBookrepository bookRepository;

    @PostMapping("/")
    public ResponseEntity createBook(@RequestBody @Valid Book book){
        Book savedBook = this.bookRepository.save(book);
        if(Utils.hasAnyNumber(book.getAuthor())) {
            return ResponseEntity.badRequest().body("Author cannot contains any number");
        }

        return ResponseEntity.status(201).body(savedBook);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return this.bookRepository.findById(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.notFound().build());
    }
}
