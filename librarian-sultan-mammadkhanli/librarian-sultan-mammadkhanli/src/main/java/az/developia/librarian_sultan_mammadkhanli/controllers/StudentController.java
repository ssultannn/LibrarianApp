package az.developia.librarian_sultan_mammadkhanli.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.BookRentEntity;
import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.request.StudentAddRequest;
import az.developia.librarian_sultan_mammadkhanli.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Добавление нового студента
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_STUDENT')")  // Только для библиотекаря
    public void add(@Valid @RequestBody StudentAddRequest req, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException("The completeness of the information is violated", br);
        }
        studentService.add(req);
    }

    // Получить все книги, арендованные студентом
    @GetMapping("/{studentId}/rented-books")
    @PreAuthorize("hasAuthority('ROLE_GET_RENTED_BOOKS')")  // Для студента или библиотекаря
    public List<BookEntity> getAllBooksByStudent(@PathVariable Integer studentId) {
        return studentService.getAllRentedBooks(studentId);
    }

    // Получить все книги, которые студент должен вернуть
    @GetMapping("/{studentId}/books-to-return")
    @PreAuthorize("hasAuthority('ROLE_GET_BOOKS_TO_RETURN')")  // Для студента или библиотекаря
    public List<BookEntity> getBooksToReturn(@PathVariable Integer studentId) {
        return studentService.getBooksToReturn(studentId);
    }
}
