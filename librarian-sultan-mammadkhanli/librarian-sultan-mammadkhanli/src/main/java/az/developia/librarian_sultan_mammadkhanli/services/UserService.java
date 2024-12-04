// UserService.java
package az.developia.librarian_sultan_mammadkhanli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.librarian_sultan_mammadkhanli.entity.UserEntity;
import az.developia.librarian_sultan_mammadkhanli.repository.UserRepository;
import az.developia.librarian_sultan_mammadkhanli.request.LibrarianAddRequest;
import az.developia.librarian_sultan_mammadkhanli.request.StudentAddRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void addLibrarian(LibrarianAddRequest req) {
		UserEntity user = new UserEntity();
		user.setUsername(req.getUsername());
		user.setPassword("{noop}" + req.getPassword());
		user.setEnabled(1);
		repo.save(user);
	}
	
	public void addStudent(StudentAddRequest req) {
		UserEntity user = new UserEntity();
		user.setUsername(req.getUsername());
		user.setPassword("{noop}" + req.getPassword());
		user.setEnabled(1);
		repo.save(user);
	}


}
