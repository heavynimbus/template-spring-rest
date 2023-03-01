package com.junia.jeeproject.mapper;

import com.junia.jeeproject.dto.author.AuthorResponse;
import com.junia.jeeproject.dto.author.CreateAuthorRequest;
import com.junia.jeeproject.entity.author.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

  AuthorResponse authorEntityToAuthorResponse(AuthorEntity authorEntity);

  AuthorEntity createAuthorRequestToAuthorEntity(
      CreateAuthorRequest createAuthorRequest);

  void updateEntity(@MappingTarget AuthorEntity entity, CreateAuthorRequest createAuthorRequest);
}
