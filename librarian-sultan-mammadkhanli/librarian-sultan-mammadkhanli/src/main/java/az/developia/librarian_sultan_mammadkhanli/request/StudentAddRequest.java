package az.developia.librarian_sultan_mammadkhanli.request;

import lombok.Data;

@Data
public class StudentAddRequest {
	private String username;
	private String password;
	private String name;
	private String surname;
	private String phone;
}
