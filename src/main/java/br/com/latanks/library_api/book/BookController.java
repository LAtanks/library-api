package br.com.latanks.library_api.book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) Long id, @RequestParam(required = false) String title) {
        if(id != null){
            List<Book> books = List.of(this.bookRepository.findById(id).get());
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }

        if(title != null){
            List<Book> books = this.bookRepository.findAllByTitleContainingIgnoreCase(title);

            return books != null ? ResponseEntity.ok(books) : ResponseEntity.notFound().build();
        }


        return ResponseEntity.badRequest().build();
    }


}
