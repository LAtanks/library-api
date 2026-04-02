package br.com.latanks.library_api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.latanks.library_api.exception.impl.InvalidCredentialsExceptions;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        User existingUser = this.userRepository.findByEmail(user.getEmail());

        if(existingUser != null) {
            throw new InvalidCredentialsExceptions("Email already exists");
        }

        if(hasAnyNumber(user.getName())) {
            throw new InvalidCredentialsExceptions("Name cannot contains any number");
        }

        User savedUser = this.userRepository.save(user);
        
        System.out.println("User created: " + savedUser.getName() + " with email: " + savedUser.getEmail() + " Id: " + savedUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new InvalidCredentialsExceptions("User not found"));

        User updatedUser = this.userRepository.save(user);
        updatedUser.setId(existingUser.getId());
        
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new InvalidCredentialsExceptions("User not found"));

        this.userRepository.delete(existingUser);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return this.userRepository.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private static boolean hasAnyNumber(String str){
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
