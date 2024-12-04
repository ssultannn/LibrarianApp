package az.developia.librarian_sultan_mammadkhanli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.developia.librarian_sultan_mammadkhanli.entity.AuthorityListEntity;


@Repository
public interface AuthorityListRepository extends JpaRepository<AuthorityListEntity, Integer>{

}
