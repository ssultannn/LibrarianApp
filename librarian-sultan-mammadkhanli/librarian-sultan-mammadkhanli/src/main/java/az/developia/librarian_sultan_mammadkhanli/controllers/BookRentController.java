package az.developia.librarian_sultan_mammadkhanli.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.request.RentRequest;
import az.developia.librarian_sultan_mammadkhanli.response.BookRentResponse;
import az.developia.librarian_sultan_mammadkhanli.services.BookRentService;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class BookRentController {

    private final BookRentService bookRentService;

    @Autowired
    public BookRentController(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }

    @PostMapping("/rent")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_RENT_BOOK')")
    public void rentBook(@Valid @RequestBody RentRequest rentRequest, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("Invalid data provided", br);
        }

        // Используем сервис для аренды книги
        bookRentService.rentBook(rentRequest.getBookId(), rentRequest.getStudentId());
    }

    @PutMapping("/return/{id}")
    @PreAuthorize("hasAuthority('ROLE_RETURN_BOOK')")
    public void returnBook(@PathVariable Long id) {
        // Используем сервис для возврата книги
        bookRentService.returnBook(id);
    }

    @GetMapping("/issued")
    @PreAuthorize("hasAuthority('ROLE_GET_ISSUED_BOOKS')")
    public List<BookRentResponse> getIssuedBooks() {
        // Получаем все активные аренды
        return bookRentService.getIssuedBooks().stream()
            .map(bookRentEntity -> new BookRentResponse(bookRentEntity))  // Преобразуем в нужный формат ответа
            .toList();
    }

    @GetMapping("/delayed")
    @PreAuthorize("hasAuthority('ROLE_GET_DELAYED_BOOKS')")
    public List<BookRentResponse> getDelayedBooks() {
        // Получаем все задержанные книги
        return bookRentService.getDelayedBooks().stream()
            .map(bookRentEntity -> new BookRentResponse(bookRentEntity))  // Преобразуем в нужный формат ответа
            .toList();
    }

}
