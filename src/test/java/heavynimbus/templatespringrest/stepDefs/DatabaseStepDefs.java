package heavynimbus.templatespringrest.stepDefs;

import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import heavynimbus.templatespringrest.entity.author.AuthorRepository;
import heavynimbus.templatespringrest.entity.book.BookEntity;
import heavynimbus.templatespringrest.entity.book.BookRepository;
import heavynimbus.templatespringrest.repository.TestRepository;
import heavynimbus.templatespringrest.world.World;
import io.cucumber.java.en.Given;
import java.util.Random;
import java.util.random.RandomGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatabaseStepDefs {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final TestRepository testRepository;
  private final World world;

  @Given("there are no authors in the database")
  public void there_are_no_authors() {
    testRepository.deleteAllAuthors();
  }

  @Given("there are {int} authors in the database")
  public void there_are_authors(int count) {
    for (int i = 0; i < count; i++) {
      authorRepository.save(AuthorEntityFactory.create());
    }
  }

  @Given("there are no books in the database")
  public void there_are_no_books() {
    testRepository.deleteAllBooks();
  }

  @Given("there are {int} books in the database")
  public void there_are_books(int count) {
    AuthorEntity author = authorRepository.save(AuthorEntityFactory.create());
    for (int i = 0; i < count; i++) {
      bookRepository.save(BookEntityFactory.create(author));
    }
  }

  static class AuthorEntityFactory {

    public static AuthorEntity create() {
      AuthorEntity author = new AuthorEntity();
      author.setName("Author " + System.currentTimeMillis());
      return author;
    }
  }

  static class BookEntityFactory {

    static RandomGenerator randomGenerator = new Random();

    public static BookEntity create(AuthorEntity author) {
      BookEntity book = new BookEntity();
      book.setTitle("Title " + System.currentTimeMillis());
      book.setPrice(randomGenerator.nextDouble());
      book.setAuthor(author);
      return book;
    }
  }
}
