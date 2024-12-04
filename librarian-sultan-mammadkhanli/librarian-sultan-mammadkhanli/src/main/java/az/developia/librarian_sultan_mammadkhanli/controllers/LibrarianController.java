package az.developia.librarian_sultan_mammadkhanli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.request.LibrarianAddRequest;
import az.developia.librarian_sultan_mammadkhanli.services.LibrarianService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    @Autowired
    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@Valid @RequestBody LibrarianAddRequest req, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("Validation failed: " + br.getFieldError().getDefaultMessage());
        }
        librarianService.add(req);
    }
}
