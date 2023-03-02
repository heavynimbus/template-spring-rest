package com.junia.jeeproject.mapper;


import com.junia.jeeproject.dto.book.BookResponse;
import com.junia.jeeproject.dto.book.CreateBookRequest;
import com.junia.jeeproject.entity.author.AuthorEntity;
import com.junia.jeeproject.entity.book.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

  BookResponse bookEntityToBookResponse(BookEntity bookEntity);

  @Mapping(target = "author", source = "authorEntity")
  BookEntity createBookRequestToBookEntity(CreateBookRequest createBookRequest,
      AuthorEntity authorEntity);

  @Mapping(target = "author", source = "authorEntity")
  void updateEntity(@MappingTarget BookEntity entity, CreateBookRequest createBookRequest,
      AuthorEntity authorEntity);
}
