package az.developia.librarian_sultan_mammadkhanli.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public void login() {
        // Placeholder for login logic
    }
}