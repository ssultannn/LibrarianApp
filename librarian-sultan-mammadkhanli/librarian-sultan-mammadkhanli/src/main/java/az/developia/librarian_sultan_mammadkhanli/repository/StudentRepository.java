package az.developia.librarian_sultan_mammadkhanli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	Optional<StudentEntity> findById(Integer studentId);

}
