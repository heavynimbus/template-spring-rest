package heavynimbus.templatespringrest.dto.author;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorResponse {
  private Integer id;
  private String name;
}
