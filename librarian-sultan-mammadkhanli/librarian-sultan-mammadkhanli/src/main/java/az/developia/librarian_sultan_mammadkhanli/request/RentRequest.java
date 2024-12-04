package az.developia.librarian_sultan_mammadkhanli.request;

import lombok.Data;

@Data
public class RentRequest {
    private Long bookId;
    private Integer studentId;
}
