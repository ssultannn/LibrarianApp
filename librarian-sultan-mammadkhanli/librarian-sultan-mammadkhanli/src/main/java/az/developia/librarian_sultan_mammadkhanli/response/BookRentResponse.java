package az.developia.librarian_sultan_mammadkhanli.response;

import az.developia.librarian_sultan_mammadkhanli.entity.BookRentEntity;
import java.time.LocalDate;

public class BookRentResponse {

    private Long rentId;
    private String bookTitle;
    private String studentName;
    private LocalDate rentDate;
    private LocalDate returnDate;

    public BookRentResponse(BookRentEntity bookRentEntity) {
        this.rentId = bookRentEntity.getId();
        this.bookTitle = bookRentEntity.getBook().getName();
        this.studentName = bookRentEntity.getStudent().getName();
        this.rentDate = bookRentEntity.getRentDate();
        this.returnDate = bookRentEntity.getReturnDate();
    }

    // Getters and Setters
}
