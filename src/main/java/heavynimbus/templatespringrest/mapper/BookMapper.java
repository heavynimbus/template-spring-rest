package heavynimbus.templatespringrest.mapper;


import heavynimbus.templatespringrest.dto.book.BookResponse;
import heavynimbus.templatespringrest.dto.book.CreateBookRequest;
import heavynimbus.templatespringrest.entity.author.AuthorEntity;
import heavynimbus.templatespringrest.entity.book.BookEntity;
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
