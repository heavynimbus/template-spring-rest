package com.junia.jeeproject.dto.book;

import lombok.Data;

@Data
public class CreateBookRequest {

  private String title;

  private Double price;

  private Integer authorId;
}
