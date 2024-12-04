package az.developia.librarian_sultan_mammadkhanli.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import az.developia.librarian_sultan_mammadkhanli.entity.BookEntity;
import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.repository.BookRentRepository;
import az.developia.librarian_sultan_mammadkhanli.repository.BookRepository;
import az.developia.librarian_sultan_mammadkhanli.request.BookAddRequest;
import az.developia.librarian_sultan_mammadkhanli.request.UpdateBookRequest;
import az.developia.librarian_sultan_mammadkhanli.response.BookResponse;
import az.developia.librarian_sultan_mammadkhanli.response.BookSingleResponce;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;

	@Autowired
	private BookRentRepository rentRepository;
	@Autowired
	private ModelMapper mapper;

	public BookResponse filterBooks(String name) {
		List<BookEntity> entities = repository.findSearch(name);
		List<BookSingleResponce> resp = entities.stream().map(e -> {
			BookSingleResponce s = new BookSingleResponce();
			s.setId(e.getId());
			s.setName(e.getName());
			return s;
		}).toList();

		BookResponse r = new BookResponse();
		r.setBooks(resp);
		return r;
	}

	public void add(BookAddRequest b) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		BookEntity entity = new BookEntity();
		mapper.map(b, entity);
		repository.save(entity);
	}

	public BookSingleResponce findByid(Long id) {
		return repository.findById(id).map(book -> {
			BookSingleResponce response = new BookSingleResponce();
			response.setId(book.getId());
			response.setName(book.getName());
			return response;
		}).orElseThrow(() -> new MyException("Книга с таким id не найдена: " + id));
	}

	public void deleteById(Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		   if (rentRepository.existsById(id)) {
	            throw new MyException("Cannot delete book. It is currently rented.");
	        }
		   rentRepository.deleteById(id);
	}

	public void update(UpdateBookRequest request) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if (request.getId() == null) {
			throw new MyException("Book ID cannot be null");
		}

		Optional<BookEntity> existingBook = repository.findById(request.getId());
		if (existingBook.isPresent()) {
			BookEntity bookToUpdate = existingBook.get();
			bookToUpdate.setName(request.getName());
			bookToUpdate.setPage_count(request.getPage_count());
			bookToUpdate.setQuantity(request.getQuantity());

			repository.save(bookToUpdate);
		} else {
			throw new MyException("Book with ID " + request.getId() + " not found");
		}
		
	}

	public BookResponse findPagination(Long begin, Long length) {

		BookResponse r = new BookResponse();

		List<BookEntity> entities = repository.findPagination(begin, length);
		List<BookSingleResponce> resp = new ArrayList<>();
		for (BookEntity e : entities) {
			BookSingleResponce s = new BookSingleResponce();
			s.setId(e.getId());
			s.setName(e.getName());
			resp.add(s);
		}
		r.setBooks(resp);
		return r;
	}

	public BookResponse searchForStudent(String query) {
		BookResponse r = new BookResponse();

		List<BookEntity> entities = repository.searchForStudent(query);
		List<BookSingleResponce> resp = new ArrayList<>();
		for (BookEntity e : entities) {
			BookSingleResponce s = new BookSingleResponce();
			s.setId(e.getId());
			s.setName(e.getName());
			resp.add(s);
		}
		r.setBooks(resp);
		return r;
	}

	public List<BookEntity> getBorrowedBooksByStudent(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}
}
