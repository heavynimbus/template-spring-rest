package com.junia.jeeproject.dto.author;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorRequest {

  @NotBlank(message = "Author name is mandatory")
  private String name;
}
