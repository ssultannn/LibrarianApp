package az.developia.librarian_sultan_mammadkhanli.entity;

import az.developia.librarian_sultan_mammadkhanli.annotations.CheckThereIs;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "librarians")
@Data
public class LibrarianEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Librarian name cannot be null")
    @NotBlank(message = "Librarian name cannot be empty")
    private String name;

    private String surname;

    @NotNull(message = "Username cannot be null")
    @Size(min = 2, max = 100, message = "Username must be between {min} and {max} characters long.")
 
    private String username;

 
    private String phone;

    @Size(max = 255, message = "Address cannot exceed {max} characters")
    private String address;
}
