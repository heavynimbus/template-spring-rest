package heavynimbus.templatespringrest.service;

import heavynimbus.templatespringrest.entity.book.BookEntity;
import heavynimbus.templatespringrest.entity.book.BookRepository;
import heavynimbus.templatespringrest.exception.client.NotFoundException;
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
