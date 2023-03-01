package com.junia.jeeproject.service;

import com.junia.jeeproject.entity.book.BookEntity;
import com.junia.jeeproject.entity.book.BookRepository;
import com.junia.jeeproject.exception.client.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  public List<BookEntity> findAll() {
    return bookRepository.findAll();
  }

  public BookEntity findById(Integer id) {
    return bookRepository.findById(id).orElseThrow(
        () -> new NotFoundException("Book not found with given id").withDetail("id", id));
  }

  public BookEntity save(BookEntity book) {
    return bookRepository.save(book);
  }

  public void delete(BookEntity book) {
    bookRepository.delete(book);
  }
}
