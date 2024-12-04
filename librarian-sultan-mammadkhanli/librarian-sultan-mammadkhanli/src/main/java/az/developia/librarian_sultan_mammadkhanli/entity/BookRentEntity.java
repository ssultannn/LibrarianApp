package az.developia.librarian_sultan_mammadkhanli.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name = "book_rent")
@Data
public class BookRentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @Column(nullable = false)
    private LocalDate rentDate;  // Дата начала аренды

    @Column
    private LocalDate returnDate; // Дата возврата книги

    @Column(nullable = false)
    private boolean active; // Статус аренды (активна или нет)
}

