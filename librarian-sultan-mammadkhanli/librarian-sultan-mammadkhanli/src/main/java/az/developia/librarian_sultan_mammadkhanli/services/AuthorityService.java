// AuthorityService.java
package az.developia.librarian_sultan_mammadkhanli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import az.developia.librarian_sultan_mammadkhanli.repository.AuthorityRepository;
import az.developia.librarian_sultan_mammadkhanli.request.LibrarianAddRequest;
import az.developia.librarian_sultan_mammadkhanli.request.StudentAddRequest;

@Service
public class AuthorityService {

    private final AuthorityRepository repo;

    @Autowired
    public AuthorityService(AuthorityRepository repo) {
        this.repo = repo;
    }

    public void addLibrarianAuthorities(LibrarianAddRequest req) {
        repo.addLibrarianAuthorities(req.getUsername());
    }

    public void addStudentAuthorities(StudentAddRequest req) {
        repo.addStudentAuthorities(req.getUsername());
    }
}
