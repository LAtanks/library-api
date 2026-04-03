package br.com.latanks.library_api.book;

import java.util.List;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name = "tb_books")
@Entity
@Data
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(name = "title", nullable = false)
    @Size(max = 50, message = "Title must be between 1 and 50 characters")
    private String title;

    @Size(max = 50, message = "Author must be at most 50 characters")
    @NotBlank(message = "Author cannot be blank")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "Description cannot be blank")
    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Category> category;
}
