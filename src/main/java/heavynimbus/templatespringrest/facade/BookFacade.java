package heavynimbus.templatespringrest.facade;

import heavynimbus.templatespringrest.dto.book.BookResponse;
import heavynimbus.templatespringrest.dto.book.CreateBookRequest;
import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import heavynimbus.templatespringrest.entity.book.BookEntity;
import heavynimbus.templatespringrest.mapper.BookMapper;
import heavynimbus.templatespringrest.service.AuthorService;
import heavynimbus.templatespringrest.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookFacade {

  private final BookService bookService;
  private final AuthorService authorService;
  private final BookMapper bookMapper;

  public List<BookResponse> findAll() {
    return bookService.findAll()
        .stream()
        .map(bookMapper::bookEntityToBookResponse)
        .toList();
  }

  public BookResponse findById(Integer id) {
    BookEntity book = bookService.findById(id);
    return bookMapper.bookEntityToBookResponse(book);
  }

  public BookResponse create(CreateBookRequest createBookRequest) {
    AuthorEntity authorEntity = authorService.findById(createBookRequest.getAuthorId());
    BookEntity book = bookMapper.createBookRequestToBookEntity(createBookRequest, authorEntity);
    BookEntity createdBook = bookService.save(book);
    return bookMapper.bookEntityToBookResponse(createdBook);
  }

  public BookResponse update(Integer bookId, CreateBookRequest createBookRequest) {
    BookEntity book = bookService.findById(bookId);
    AuthorEntity authorEntity = authorService.findById(createBookRequest.getAuthorId());
    bookMapper.updateEntity(book, createBookRequest, authorEntity);
    book = bookService.save(book);
    return bookMapper.bookEntityToBookResponse(book);
  }

  public void delete(Integer bookId) {
    BookEntity book = bookService.findById(bookId);
    bookService.delete(book);
  }
}
