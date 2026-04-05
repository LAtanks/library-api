package br.com.latanks.library_api.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.latanks.library_api.Utils.Utils;
import br.com.latanks.library_api.exception.impl.BookBorrowFailedException;
import br.com.latanks.library_api.exception.impl.InvalidCredentialsExceptions;
import br.com.latanks.library_api.user.IUserRepository;
import br.com.latanks.library_api.user.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private IBookrepository bookRepository;

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity createBook(@RequestBody @Valid Book book){
        Book savedBook = this.bookRepository.save(book);

        return Utils.hasAnyNumber(book.getAuthor()) 
        ? ResponseEntity.status(201).body(savedBook)
        : ResponseEntity.badRequest().body("Author cannot contains any number");
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) Long id, @RequestParam(required = false) String title) {
        if(id != null){
            List<Book> books = List.of(this.bookRepository.findById(id).orElseThrow(
                () -> new InvalidCredentialsExceptions("Livro não encontrado no sistema")));

            return ResponseEntity.status(HttpStatus.OK).body(books);
        }

        if(title != null){
            List<Book> books = this.bookRepository.findAllByTitleContainingIgnoreCase(title).get();

            if(books.isEmpty())
                throw new InvalidCredentialsExceptions("Não existe nenhum livro contendo este nome no titulo");
            
            return !books.isEmpty() 
            ? ResponseEntity.status(HttpStatus.OK).body(books)
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/borrowBook/{id}")
    public ResponseEntity borrowBook(@PathVariable Long id, @RequestBody Long userId){
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new InvalidCredentialsExceptions("Livro não encontrado"));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidCredentialsExceptions("Usuario não encontrado"));

        if(book.getLentUser() != null)
            throw new BookBorrowFailedException("Este livro já está emprestado");
        if(user.getBorrowedBooks().contains(book))
            throw new BookBorrowFailedException("Este usuario ja pegou este livro emprestado");
        if(user.getBorrowedBooks().size() > 3)
            throw new BookBorrowFailedException("Atingiu o limite para emprestimos de livros");
        
        user.getBorrowedBooks().add(book);
        book.setLentUser(user);

        this.bookRepository.save(book);
        this.userRepository.save(user);
        
        System.out.println(book);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Emprestado com sucesso");
    }

}
