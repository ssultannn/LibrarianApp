package az.developia.librarian_sultan_mammadkhanli.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import az.developia.librarian_sultan_mammadkhanli.entity.LibrarianEntity;
import az.developia.librarian_sultan_mammadkhanli.exception.MyException;
import az.developia.librarian_sultan_mammadkhanli.repository.LibrarianRepository;
import az.developia.librarian_sultan_mammadkhanli.request.LibrarianAddRequest;
import jakarta.transaction.Transactional;

@Service
public class LibrarianService {

    private final LibrarianRepository repo;
    private final UserService userService;
    private final AuthorityService authorityService;


    @Autowired
    public LibrarianService(LibrarianRepository repo, UserService userService, AuthorityService authorityService, ModelMapper mapper) {
        this.repo = repo;
        this.userService = userService;
        this.authorityService = authorityService;
   
    }
    @Transactional
    public void add(LibrarianAddRequest req) {
        // Проверка уникальности имени пользователя
        if (repo.findByUsername(req.getUsername()) != null) {
            throw new MyException("Username already exists.");
        }

        // Маппинг данных запроса в сущность
        LibrarianEntity librarian = new LibrarianEntity();
        librarian.setUsername(req.getUsername());
        librarian.setName(req.getName());
        librarian.setSurname(req.getSurname());
        librarian.setAddress(req.getAddress());
        librarian.setPhone(req.getPhone());

        // Сохранение нового библиотекаря
        repo.save(librarian);

        // Вызов методов для добавления пользователя и прав
        userService.addLibrarian(req);
        authorityService.addLibrarianAuthorities(req);
    }
}
