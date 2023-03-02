package heavynimbus.templatespringrest.facade;

import heavynimbus.templatespringrest.dto.author.AuthorResponse;
import heavynimbus.templatespringrest.dto.author.CreateAuthorRequest;
import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import heavynimbus.templatespringrest.mapper.AuthorMapper;
import heavynimbus.templatespringrest.service.AuthorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorFacade {

  private final AuthorMapper authorMapper;
  private final AuthorService authorService;

  public List<AuthorResponse> findAll() {
    return authorService
        .findAll()
        .stream()
        .map(authorMapper::authorEntityToAuthorResponse)
        .toList();
  }

  public AuthorResponse findById(Integer id) {
    AuthorEntity author = authorService.findById(id);
    return authorMapper.authorEntityToAuthorResponse(author);
  }

  public AuthorResponse create(CreateAuthorRequest createAuthorRequest) {
    AuthorEntity authorEntity = authorMapper.createAuthorRequestToAuthorEntity(createAuthorRequest);
    authorEntity = authorService.save(authorEntity);
    return authorMapper.authorEntityToAuthorResponse(authorEntity);
  }

  public AuthorResponse update(Integer id, CreateAuthorRequest createAuthorRequest) {
    AuthorEntity authorEntity = authorService.findById(id);
    authorMapper.updateEntity(authorEntity, createAuthorRequest);
    authorEntity = authorService.save(authorEntity);
    return authorMapper.authorEntityToAuthorResponse(authorEntity);
  }

  public void delete(Integer id) {
    AuthorEntity authorEntity = authorService.findById(id);
    authorService.delete(authorEntity);
  }
}
