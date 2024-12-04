package az.developia.librarian_sultan_mammadkhanli.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.BookRentEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.StudentEntity;
import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.repository.BookRentRepository;
import az.developia.librarian_sultan_mammadkhanli.repository.BookRepository;
import az.developia.librarian_sultan_mammadkhanli.repository.StudentRepository;

@Service
public class BookRentService {

    private final BookRentRepository bookRentRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public BookRentService(BookRentRepository bookRentRepository, BookRepository bookRepository, StudentRepository studentRepository) {
        this.bookRentRepository = bookRentRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    // Аренда книги
    public BookRentEntity rentBook(Long bookId, Integer studentId) {
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        StudentEntity student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        // Проверка на наличие доступных экземпляров
        if (book.getQuantity() <= 0) {
            throw new RuntimeException("No available copies of the book");
        }

        // Создание записи аренды
        BookRentEntity bookRent = new BookRentEntity();
        bookRent.setBook(book);
        bookRent.setStudent(student);
        bookRent.setRentDate(LocalDate.now());
        bookRent.setActive(true);

        // Уменьшаем количество доступных экземпляров
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);  // Сохраняем изменения в книге

        return bookRentRepository.save(bookRent);  // Сохраняем аренду в базе данных
    }

    // Возврат книги
 // Возврат книги
    public BookRentEntity returnBook(Long id) {
        BookRentEntity bookRent = bookRentRepository.findById(id)
                .orElseThrow(() -> new MyException("Rent not found"));

        if (bookRent.getReturnDate() != null) {
            throw new MyException("Book already returned");
        }

        // Update rental status
        bookRent.setReturnDate(LocalDate.now());
        bookRent.setActive(false);

        // Update book quantity
        BookEntity book = bookRent.getBook();
        if (book == null) {
            throw new MyException("Associated book not found");
        }
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        return bookRentRepository.save(bookRent);
    }

    // Получить все аренды
    public List<BookRentEntity> getAllRents() {
        return bookRentRepository.findAll();  // Возвращаем все аренды
    }

    // Получить все активные аренды
    public List<BookRentEntity> getIssuedBooks() {
        return bookRentRepository.findAllByActiveTrue();  // Возвращаем все активные аренды
    }

    // Получить все завершенные аренды
    public List<BookRentEntity> getReturnedBooks() {
        return bookRentRepository.findByActiveFalse();  // Возвращаем все завершенные аренды
    }

    // Получить все задержанные книги
    public List<BookRentEntity> getDelayedBooks() {
        LocalDate today = LocalDate.now();
        return bookRentRepository.findAll().stream()
            .filter(rent -> rent.getReturnDate() == null && rent.getRentDate().plusDays(14).isBefore(today))
            .collect(Collectors.toList());
    }

    // Получить студентов с задержанными книгами
    public List<StudentEntity> getStudentsWithDelayedBooks() {
        return getDelayedBooks().stream()
            .map(BookRentEntity::getStudent)  // Извлекаем студента из записи аренды
            .distinct()  // Убираем дубликаты
            .collect(Collectors.toList());
    }

}
