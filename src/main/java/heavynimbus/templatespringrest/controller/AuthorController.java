package heavynimbus.templatespringrest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import heavynimbus.templatespringrest.dto.ExceptionResponse;
import heavynimbus.templatespringrest.dto.author.AuthorResponse;
import heavynimbus.templatespringrest.dto.author.CreateAuthorRequest;
import heavynimbus.templatespringrest.facade.AuthorFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController implements AuthorControllerDocumentation {

  private final AuthorFacade authorFacade;

  @GetMapping
  @ResponseStatus(OK)
  public List<AuthorResponse> getAuthors() {
    return authorFacade.findAll();
  }

  @ResponseStatus(OK)
  @GetMapping("/{authorId}")
  public AuthorResponse getAuthorById(@PathVariable Integer authorId) {
    return authorFacade.findById(authorId);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public AuthorResponse createAuthor(@Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
    return authorFacade.create(createAuthorRequest);
  }

  @ResponseStatus(OK)
  @PutMapping("/{authorId}")
  public AuthorResponse updateAuthor(@PathVariable Integer authorId,
      @Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
    return authorFacade.update(authorId, createAuthorRequest);
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping("/{authorId}")
  public void deleteAuthor(@PathVariable Integer authorId) {
    authorFacade.delete(authorId);
  }

}
