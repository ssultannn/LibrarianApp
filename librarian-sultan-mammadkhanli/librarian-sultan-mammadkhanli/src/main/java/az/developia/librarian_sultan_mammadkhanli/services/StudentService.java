// StudentService.java
package az.developia.librarian_sultan_mammadkhanli.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.BookRentEntity;
import az.developia.librarian_sultan_mammadkhanli.entity.StudentEntity;
import az.developia.librarian_sultan_mammadkhanli.repository.BookRentRepository;
import az.developia.librarian_sultan_mammadkhanli.repository.StudentRepository;
import az.developia.librarian_sultan_mammadkhanli.request.StudentAddRequest;

@Service
public class StudentService {

	private final StudentRepository repo;
	 private final BookRentRepository bookRentRepository;
	private final UserService userService;
	private final AuthorityService authorityService;
	private final ModelMapper mapper;

	@Autowired
	public StudentService(StudentRepository repo, UserService userService, AuthorityService authorityService, BookRentRepository bookRentRepository,
			ModelMapper mapper) {
		this.repo = repo;
		   this.bookRentRepository = bookRentRepository;
		this.userService = userService;
		this.authorityService = authorityService;
		this.mapper = mapper;
	}

	public void add(StudentAddRequest req) {
		// Manually mapping properties from SellerAddRequest to SellerEntity
		StudentEntity student = new StudentEntity();
		mapper.map(req, student);

		repo.save(student);

		// Call userService and authorityService methods
		userService.addStudent(req);
		authorityService.addStudentAuthorities(req);
	}
    // Получить все арендуемые книги студента
	  public List<BookEntity> getAllRentedBooks(Integer studentId) {
	        return bookRentRepository.findByStudentId(studentId)
	                                 .stream()
	                                 .map(BookRentEntity::getBook)  // Извлечь книги из сущностей аренды
	                                 .collect(Collectors.toList());
	    }

	    // Получить книги, которые студент должен вернуть
	    public List<BookEntity> getBooksToReturn(Integer studentId) {
	        return bookRentRepository.findAllByStudentIdAndActiveTrue(studentId)
	                                 .stream()
	                                 .map(BookRentEntity::getBook)  // Извлечь книги из сущностей аренды
	                                 .collect(Collectors.toList());
	    }
}
