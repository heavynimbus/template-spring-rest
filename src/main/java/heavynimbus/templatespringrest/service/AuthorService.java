package heavynimbus.templatespringrest.service;

import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import heavynimbus.templatespringrest.entity.author.AuthorRepository;
import heavynimbus.templatespringrest.exception.client.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorRepository authorRepository;

  public List<AuthorEntity> findAll() {
    return authorRepository.findAll();
  }

  public AuthorEntity findById(Integer id) {
    return authorRepository.findById(id)
        .orElseThrow(() ->
            new NotFoundException("Author not found with given id")
                .withDetail("id", id));
  }

  public AuthorEntity save(AuthorEntity authorEntity) {
    return authorRepository.save(authorEntity);
  }

  public void delete(AuthorEntity authorEntity) {
    authorRepository.delete(authorEntity);
  }
}
