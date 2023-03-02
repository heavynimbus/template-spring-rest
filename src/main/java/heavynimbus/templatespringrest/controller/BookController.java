package heavynimbus.templatespringrest.controller;

import heavynimbus.templatespringrest.dto.book.BookResponse;
import heavynimbus.templatespringrest.dto.book.CreateBookRequest;
import heavynimbus.templatespringrest.facade.BookFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Books")
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

  private final BookFacade bookFacade;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all books")
  public List<BookResponse> findAll() {
    return bookFacade.findAll();
  }

  @GetMapping("/{bookId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all books")
  public BookResponse findById(@PathVariable Integer bookId) {
    return bookFacade.findById(bookId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create book")
  public BookResponse create(@Valid @RequestBody CreateBookRequest createBookRequest) {
    return bookFacade.create(createBookRequest);
  }

  @PutMapping("/{bookId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Update book")
  public BookResponse update(@PathVariable Integer bookId,
      @Valid @RequestBody CreateBookRequest createBookRequest) {
    return bookFacade.update(bookId, createBookRequest);
  }

  @DeleteMapping("/{bookId}")
  @Operation(summary = "Delete book")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer bookId) {
    bookFacade.delete(bookId);
  }
}
