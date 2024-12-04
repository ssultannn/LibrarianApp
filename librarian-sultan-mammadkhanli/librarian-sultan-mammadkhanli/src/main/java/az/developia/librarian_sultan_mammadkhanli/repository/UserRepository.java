package az.developia.librarian_sultan_mammadkhanli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> { 
    Optional<UserEntity> findById(Long userId);  
}
