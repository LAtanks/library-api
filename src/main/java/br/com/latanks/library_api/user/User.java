package br.com.latanks.library_api.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.latanks.library_api.book.Book;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "tb_users")
@Entity(name = "tb_users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"borrowedBooks"})
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email cannot be blank or blank")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    
    @OneToMany(mappedBy = "lentUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<Book> borrowedBooks = new HashSet<>();

    public void addBooks(Book book){
        if(borrowedBooks == null) borrowedBooks = new HashSet<>();
        borrowedBooks.add(book);
        book.setLentUser(this);

        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println(borrowedBooks.stream().toList().get(i).getTitle());
        }
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

}
