package heavynimbus.templatespringrest.dto.book;

import heavynimbus.templatespringrest.dto.author.AuthorResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponse {

  private Integer id;

  private String title;

  private Double price;

  private AuthorResponse author;
}
