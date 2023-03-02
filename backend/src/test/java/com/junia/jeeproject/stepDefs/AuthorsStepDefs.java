package com.junia.jeeproject.stepDefs;

import com.junia.jeeproject.entity.author.AuthorEntity;
import com.junia.jeeproject.entity.author.AuthorRepository;
import com.junia.jeeproject.repository.TestRepository;
import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorsStepDefs {

  private final AuthorRepository authorRepository;
  private final TestRepository testRepository;

  @Given("there are no authors in the database")
  public void there_are_no_authors() {
    testRepository.deleteAllAuthors();
  }

  @Given("there are {int} authors in the database")
  public void there_are_authors(int count) {
    authorRepository.deleteAll();
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
