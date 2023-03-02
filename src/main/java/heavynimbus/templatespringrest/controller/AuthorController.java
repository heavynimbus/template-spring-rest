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
@Tag(name = "Authors")
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

  private final AuthorFacade authorFacade;

  @GetMapping
  @ResponseStatus(OK)
  @Operation(summary = "Get all authors")
  @ApiResponse(responseCode = "200", description = "Operation success",
      content = @Content(schema = @Schema(implementation = AuthorResponse.class)))
  public List<AuthorResponse> getAuthors() {
    return authorFacade.findAll();
  }

  @ResponseStatus(OK)
  @GetMapping("/{authorId}")
  @Operation(summary = "Get author by id")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Operation success",
          content = @Content(schema = @Schema(implementation = AuthorResponse.class))),
      @ApiResponse(responseCode = "404", description = "Author not found",
          content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  public AuthorResponse getAuthorById(@PathVariable Integer authorId) {
    return authorFacade.findById(authorId);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(summary = "Create author")
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Operation success",
          content = @Content(schema = @Schema(implementation = AuthorResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request",
          content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  public AuthorResponse createAuthor(@Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
    return authorFacade.create(createAuthorRequest);
  }

  @ResponseStatus(OK)
  @PutMapping("/{authorId}")
  @Operation(summary = "Update author")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Operation success",
          content = @Content(schema = @Schema(implementation = AuthorResponse.class))),
      @ApiResponse(responseCode = "400", description = "Bad request",
          content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
      @ApiResponse(responseCode = "404", description = "Author not found",
          content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  public AuthorResponse updateAuthor(@PathVariable Integer authorId,
      @Valid @RequestBody CreateAuthorRequest createAuthorRequest) {
    return authorFacade.update(authorId, createAuthorRequest);
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping("/{authorId}")
  @Operation(summary = "Delete author")
  @ApiResponses({
      @ApiResponse(responseCode = "204", description = "Operation success"),
      @ApiResponse(responseCode = "404", description = "Author not found",
          content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  })
  public void deleteAuthor(@PathVariable Integer authorId) {
    authorFacade.delete(authorId);
  }

}
