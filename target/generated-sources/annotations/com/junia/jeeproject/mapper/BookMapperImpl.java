package com.junia.jeeproject.mapper;

import com.junia.jeeproject.dto.author.AuthorResponse;
import com.junia.jeeproject.dto.book.BookResponse;
import com.junia.jeeproject.dto.book.CreateBookRequest;
import com.junia.jeeproject.entity.author.AuthorEntity;
import com.junia.jeeproject.entity.book.BookEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-02T12:01:32+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookResponse bookEntityToBookResponse(BookEntity bookEntity) {
        if ( bookEntity == null ) {
            return null;
        }

        BookResponse.BookResponseBuilder bookResponse = BookResponse.builder();

        bookResponse.id( bookEntity.getId() );
        bookResponse.title( bookEntity.getTitle() );
        bookResponse.price( bookEntity.getPrice() );
        bookResponse.author( authorEntityToAuthorResponse( bookEntity.getAuthor() ) );

        return bookResponse.build();
    }

    @Override
    public BookEntity createBookRequestToBookEntity(CreateBookRequest createBookRequest, AuthorEntity authorEntity) {
        if ( createBookRequest == null && authorEntity == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        if ( createBookRequest != null ) {
            bookEntity.setTitle( createBookRequest.getTitle() );
            bookEntity.setPrice( createBookRequest.getPrice() );
        }
        if ( authorEntity != null ) {
            bookEntity.setAuthor( authorEntity );
            bookEntity.setId( authorEntity.getId() );
        }

        return bookEntity;
    }

    @Override
    public void updateEntity(BookEntity entity, CreateBookRequest createBookRequest, AuthorEntity authorEntity) {
        if ( createBookRequest == null && authorEntity == null ) {
            return;
        }

        if ( createBookRequest != null ) {
            entity.setTitle( createBookRequest.getTitle() );
            entity.setPrice( createBookRequest.getPrice() );
        }
        if ( authorEntity != null ) {
            entity.setAuthor( authorEntity );
            entity.setId( authorEntity.getId() );
        }
    }

    protected AuthorResponse authorEntityToAuthorResponse(AuthorEntity authorEntity) {
        if ( authorEntity == null ) {
            return null;
        }

        AuthorResponse.AuthorResponseBuilder authorResponse = AuthorResponse.builder();

        authorResponse.id( authorEntity.getId() );
        authorResponse.name( authorEntity.getName() );

        return authorResponse.build();
    }
}
