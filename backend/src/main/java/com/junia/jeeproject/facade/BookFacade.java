package com.junia.jeeproject.facade;

import com.junia.jeeproject.dto.book.BookResponse;
import com.junia.jeeproject.dto.book.CreateBookRequest;
import com.junia.jeeproject.entity.author.AuthorEntity;
import com.junia.jeeproject.entity.book.BookEntity;
import com.junia.jeeproject.mapper.BookMapper;
import com.junia.jeeproject.service.AuthorService;
import com.junia.jeeproject.service.BookService;
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
    AuthorEntity authorEntity = authorService.findById(createBookRequest.getAuthorId());
    BookEntity book = bookService.findById(bookId);
    bookMapper.updateEntity(book, createBookRequest, authorEntity);
    book = bookService.save(book);
    return bookMapper.bookEntityToBookResponse(book);
  }

  public void delete(Integer bookId) {
    BookEntity book = bookService.findById(bookId);
    bookService.delete(book);
  }
}
