package br.com.latanks.library_api.book;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.latanks.library_api.user.User;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_books")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"lentUser"})
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(name = "title", nullable = false)
    @Size(max = 250, message = "Title must be between 1 and 50 characters")
    private String title;

    @Size(max = 50, message = "Author must be at most 50 characters")
    @NotBlank(message = "Author cannot be blank")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "Description cannot be blank")
    @Lob
    @Column(name = "description", nullable = true, columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Category> category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User lentUser;

    @Column(name = "loan_start_date", nullable = true)
    private LocalDateTime loanStartDate;

    @Column(name = "loan_end_date", nullable = true)
    private LocalDateTime loanEndDate;
}
