package az.developia.librarian_sultan_mammadkhanli.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import az.developia.librarian_sultan_mammadkhanli.repository.LibrarianRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class CheckThereIsValidator implements ConstraintValidator<CheckThereIs, String> {

    @Autowired
    private LibrarianRepository repo;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            return true; // Если null, считаем валидным
        }
        return repo.findByUsername(username) == null; // Проверяем наличие имени пользователя
    }
}
