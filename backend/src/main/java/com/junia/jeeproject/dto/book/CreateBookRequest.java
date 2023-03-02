package com.junia.jeeproject.dto.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookRequest {

  @NotBlank(message = "Book title is mandatory")
  private String title;

  @NotNull(message = "Book price is mandatory")
  @Min(value = 0, message = "Book price must be greater than 0")
  private Double price;

  @NotNull(message = "Book authorId is mandatory")
  private Integer authorId;
}
