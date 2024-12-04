package az.developia.librarian_sultan_mammadkhanli.request;

import az.developia.librarian_sultan_mammadkhanli.annotations.CheckThereIs;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibrarianAddRequest {

    @NotNull
    @Size(min = 2, max = 100, message = "Username must be between {min} and {max} characters long.")
 @CheckThereIs
    private String username;

    @NotNull
    @Size(min = 5, max = 20, message = "Password must be between {min} and {max} characters long.")
    private String password;

    @NotNull(message = "Librarian name cannot be null")
    @NotBlank(message = "Librarian name cannot be empty")
    private String name;

    private String surname;

   
    private String phone;

    @Size(max = 255, message = "Address cannot exceed {max} characters")
    private String address;
}
