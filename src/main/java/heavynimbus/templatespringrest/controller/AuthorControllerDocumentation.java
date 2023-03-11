package heavynimbus.templatespringrest.controller;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

import heavynimbus.templatespringrest.dto.ExceptionResponse;
import heavynimbus.templatespringrest.dto.author.AuthorResponse;
import heavynimbus.templatespringrest.dto.author.CreateAuthorRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Authors")
public interface AuthorControllerDocumentation {
  @Operation(summary = "Get all authors")
  @ApiResponse(
      responseCode = "200",
      description = "Operation success",
      content = @Content(schema = @Schema(implementation = AuthorResponse.class)))
  List<AuthorResponse> getAuthors();

  @Operation(summary = "Get author by id")
  @ApiResponse(
      responseCode = "200",
      description = "Operation success",
      content = @Content(schema = @Schema(implementation = AuthorResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Author not found",
      content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  AuthorResponse getAuthorById(
      @Parameter(name = "authorId", description = "The id of the author you want to get", in = PATH)
          Integer authorId);

  @Operation(summary = "Create author")
  @ApiResponse(
      responseCode = "201",
      description = "Operation success",
      content = @Content(schema = @Schema(implementation = AuthorResponse.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  AuthorResponse createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest);

  @Operation(summary = "Update author")
  @ApiResponse(
      responseCode = "200",
      description = "Operation success",
      content = @Content(schema = @Schema(implementation = AuthorResponse.class)))
  @ApiResponse(
      responseCode = "400",
      description = "Bad request",
      content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  @ApiResponse(
      responseCode = "404",
      description = "Author not found",
      content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  AuthorResponse updateAuthor(
      @Parameter(name = "authorId", description = "The id of the author you want to get", in = PATH)
          Integer authorId,
      @RequestBody CreateAuthorRequest createAuthorRequest);

  @Operation(summary = "Delete author")
  @ApiResponse(responseCode = "204", description = "Operation success")
  @ApiResponse(
      responseCode = "404",
      description = "Author not found",
      content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
  void deleteAuthor(
      @Parameter(name = "authorId", description = "The id of the author you want to get", in = PATH)
          Integer authorId);
}
