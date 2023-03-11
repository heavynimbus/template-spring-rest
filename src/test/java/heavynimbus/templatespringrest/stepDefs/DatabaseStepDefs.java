package heavynimbus.templatespringrest.stepDefs;

import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import heavynimbus.templatespringrest.entity.author.AuthorRepository;
import heavynimbus.templatespringrest.repository.TestRepository;
import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DatabaseStepDefs {

  private final AuthorRepository authorRepository;
  private final TestRepository testRepository;

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
  static class AuthorEntityFactory {

    public static AuthorEntity create() {
      AuthorEntity author = new AuthorEntity();
      author.setName("Author " + System.currentTimeMillis());
      return author;
    }
  }

}
