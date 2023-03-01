package com.junia.jeeproject.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.junia.jeeproject.dto.author.AuthorResponse;
import com.junia.jeeproject.dto.author.CreateAuthorRequest;
import com.junia.jeeproject.facade.AuthorFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  public List<AuthorResponse> getAuthors() {
    return authorFacade.findAll();
  }

  @ResponseStatus(OK)
  @GetMapping("/{authorId}")
  @Operation(summary = "Get author by id")
  public AuthorResponse getAuthorById(@PathVariable Integer authorId) {
    return authorFacade.findById(authorId);
  }

  @PostMapping
  @ResponseStatus(CREATED)
  @Operation(summary = "Create author")
  public AuthorResponse createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
    return authorFacade.create(createAuthorRequest);
  }

  @ResponseStatus(OK)
  @PutMapping("/{authorId}")
  @Operation(summary = "Update author")
  public AuthorResponse updateAuthor(@PathVariable Integer authorId,
      @RequestBody CreateAuthorRequest createAuthorRequest) {
    return authorFacade.update(authorId, createAuthorRequest);
  }

  @ResponseStatus(OK)
  @DeleteMapping("/{authorId}")
  @Operation(summary = "Delete author")
  public void deleteAuthor(@PathVariable Integer authorId) {
    authorFacade.delete(authorId);
  }

}
