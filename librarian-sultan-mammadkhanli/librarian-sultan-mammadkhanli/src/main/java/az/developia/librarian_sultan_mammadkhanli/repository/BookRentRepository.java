package az.developia.librarian_sultan_mammadkhanli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.librarian_sultan_mammadkhanli.entity.BookRentEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRentRepository extends JpaRepository<BookRentEntity, Long> {

	List<BookRentEntity> findAllByActiveTrue();

	List<BookRentEntity> findAllByReturnDateIsNotNull();

	List<BookRentEntity> findAllByActiveTrueAndRentDateBefore(LocalDate date);

	List<BookRentEntity> findByActiveFalse();

	List<BookRentEntity> findByStudentId(Integer studentId);

	// Получить все активные аренды для студента
	List<BookRentEntity> findAllByStudentIdAndActiveTrue(Integer studentId);
}
