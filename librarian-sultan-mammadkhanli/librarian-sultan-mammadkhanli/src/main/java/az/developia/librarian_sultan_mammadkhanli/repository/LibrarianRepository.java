package az.developia.librarian_sultan_mammadkhanli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.librarian_sultan_mammadkhanli.entity.LibrarianEntity;
@Repository
public interface LibrarianRepository extends JpaRepository<LibrarianEntity, Integer> {
    LibrarianEntity findByUsername(String username); // Этот метод проверяет наличие библиотекаря по имени пользователя.
}