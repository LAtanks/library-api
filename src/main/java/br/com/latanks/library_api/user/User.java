package br.com.latanks.library_api.user;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "tb_users")
@Entity(name = "tb_users")
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
