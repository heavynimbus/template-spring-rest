package heavynimbus.templatespringrest.repository;

import heavynimbus.templatespringrest.entity.author.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestRepository {

  private final AuthorRepository authorRepository;
  private final EntityManager entityManager;

  @Transactional
  public void deleteAllAuthors() {
    authorRepository.deleteAll();
    Query query = entityManager.createNativeQuery("ALTER SEQUENCE author_id_seq RESTART;");
    query.executeUpdate();
  }
}
