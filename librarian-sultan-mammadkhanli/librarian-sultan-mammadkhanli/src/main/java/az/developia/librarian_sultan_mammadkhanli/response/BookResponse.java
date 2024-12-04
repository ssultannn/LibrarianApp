package az.developia.librarian_sultan_mammadkhanli.response;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
	private List<BookSingleResponce> books;

}
