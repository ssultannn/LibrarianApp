package az.developia.librarian_sultan_mammadkhanli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.request.BookAddRequest;
import az.developia.librarian_sultan_mammadkhanli.request.UpdateBookRequest;
import az.developia.librarian_sultan_mammadkhanli.response.BookResponse;
import az.developia.librarian_sultan_mammadkhanli.response.BookSingleResponce;
import az.developia.librarian_sultan_mammadkhanli.services.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_GET_ONE_BOOK')")
    public BookSingleResponce findById(@PathVariable("id") Long id) {
        return service.findByid(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_BOOK')")
    public void updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest book) {
        book.setId(id);  // Ensure the ID from the path is set in the request
        service.update(book);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_BOOK')")
    @ResponseStatus(HttpStatus.OK)
    public BookResponse findAll(@RequestParam(name = "search", required = false, defaultValue = "java") String search) {
        return service.filterBooks(search);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADD_BOOK')")
    public void addBook(@Valid @RequestBody BookAddRequest newBook, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("The completeness of the information is violated", br);
        }
        System.out.println("Received JSON: " + newBook);
        service.add(newBook);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_BOOK')")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @GetMapping("/begin/{begin}/length/{length}")
    @PreAuthorize("hasAuthority('ROLE_GET_BOOK')")
    public BookResponse findPagination(@PathVariable("begin") Long begin, @PathVariable("length") Long length) {
        return service.findPagination(begin, length);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('ROLE_SEARCH_BOOK')")
    public BookResponse searchForStudent(@RequestParam("query") String query) {
        return service.searchForStudent(query);
    }
}
