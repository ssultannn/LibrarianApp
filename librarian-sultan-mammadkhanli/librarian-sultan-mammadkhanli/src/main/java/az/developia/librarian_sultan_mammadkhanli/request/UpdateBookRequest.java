package az.developia.librarian_sultan_mammadkhanli.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateBookRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Positive
	@NotNull
	private Long id;

	private String name;
	private int page_count;
	private int quantity;
}
