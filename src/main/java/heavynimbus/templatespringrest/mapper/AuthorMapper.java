package heavynimbus.templatespringrest.mapper;

import heavynimbus.templatespringrest.dto.author.AuthorResponse;
import heavynimbus.templatespringrest.dto.author.CreateAuthorRequest;
import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

  AuthorResponse authorEntityToAuthorResponse(AuthorEntity authorEntity);

  AuthorEntity createAuthorRequestToAuthorEntity(
      CreateAuthorRequest createAuthorRequest);

  void updateEntity(@MappingTarget AuthorEntity entity, CreateAuthorRequest createAuthorRequest);
}
