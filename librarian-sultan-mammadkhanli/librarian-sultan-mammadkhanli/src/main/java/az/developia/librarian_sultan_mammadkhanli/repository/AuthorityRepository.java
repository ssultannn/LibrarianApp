package az.developia.librarian_sultan_mammadkhanli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import az.developia.librarian_sultan_mammadkhanli.entity.AuthorityEntity;
import jakarta.transaction.Transactional;

@Transactional

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Integer> {
    @Query(value = "INSERT INTO authorities(username, authority) SELECT ?1, authority FROM authority_list WHERE librarian = 1", nativeQuery = true)
    @Modifying
    void addLibrarianAuthorities(String username);
    
    @Query(value = "INSERT INTO authorities(username, authority) SELECT ?1, authority FROM authority_list WHERE student = 1", nativeQuery = true)
    @Modifying
	void addStudentAuthorities(String username);
    

}
