package com.junia.jeeproject.mapper;

import com.junia.jeeproject.dto.author.AuthorResponse;
import com.junia.jeeproject.dto.author.CreateAuthorRequest;
import com.junia.jeeproject.entity.author.AuthorEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-02T12:01:32+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Private Build)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorResponse authorEntityToAuthorResponse(AuthorEntity authorEntity) {
        if ( authorEntity == null ) {
            return null;
        }

        AuthorResponse.AuthorResponseBuilder authorResponse = AuthorResponse.builder();

        authorResponse.id( authorEntity.getId() );
        authorResponse.name( authorEntity.getName() );

        return authorResponse.build();
    }

    @Override
    public AuthorEntity createAuthorRequestToAuthorEntity(CreateAuthorRequest createAuthorRequest) {
        if ( createAuthorRequest == null ) {
            return null;
        }

        AuthorEntity authorEntity = new AuthorEntity();

        authorEntity.setName( createAuthorRequest.getName() );

        return authorEntity;
    }

    @Override
    public void updateEntity(AuthorEntity entity, CreateAuthorRequest createAuthorRequest) {
        if ( createAuthorRequest == null ) {
            return;
        }

        entity.setName( createAuthorRequest.getName() );
    }
}
