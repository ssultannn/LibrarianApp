package az.developia.librarian_sultan_mammadkhanli.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BookAddRequest {
	private String name;
	private int page_count;
	private int quantity;

}
