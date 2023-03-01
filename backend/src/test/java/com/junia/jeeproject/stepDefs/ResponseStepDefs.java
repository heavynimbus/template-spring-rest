package com.junia.jeeproject.stepDefs;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.junia.jeeproject.world.World;
import com.junia.jeeproject.world.WorldKey;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.ResultActions;

@RequiredArgsConstructor
public class ResponseStepDefs {

  private final World world;

  @Then("response status is {httpStatus}")
  public void response_status_is(HttpStatus httpStatus) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(status().is(httpStatus.value()));
  }

  @Then("response header {string} is {string}")
  public void response_header_is(String headerName, String headerValue) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(header().string(headerName, headerValue));
  }

  @Then("response body is:")
  public void response_body_is(String json) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(content().json(json));
  }
}
