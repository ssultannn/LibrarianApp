package az.developia.librarian_sultan_mammadkhanli.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; // Add this import

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;


@Repository // Add this annotation
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	@Query("SELECT b FROM BookEntity b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<BookEntity> findSearch(@Param("name") String name);
	
	@Query(value = "SELECT * FROM books  limit ?1,?2", nativeQuery = true)
	List<BookEntity> findPagination(Long begin, Long length);
	@Query(value = "SELECT * FROM books  WHERE name LIKE %?1%", nativeQuery = true)
	List<BookEntity> searchForStudent(String query);
}
