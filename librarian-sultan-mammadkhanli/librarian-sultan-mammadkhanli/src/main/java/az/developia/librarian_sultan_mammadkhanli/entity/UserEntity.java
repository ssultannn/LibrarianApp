package az.developia.librarian_sultan_mammadkhanli.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data

public class UserEntity {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "password")
    private String password;

    // Getters and Setters
}
