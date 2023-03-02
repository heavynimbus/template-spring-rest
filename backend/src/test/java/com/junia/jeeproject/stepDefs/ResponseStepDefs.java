package com.junia.jeeproject.stepDefs;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  @Then("response string value at {string} is {string}")
  public void response_string_value_at_path_is(String path, String value) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).isString());
    result.andExpect(jsonPath(path).value(value));
  }

  @Then("response string value at {string} matches {string}")
  public void response_string_value_at_path_matches_pattern(String path, String pattern)
      throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).isString());
    result.andExpect(jsonPath(path, matchesPattern(pattern)));
  }

  @Then("all response string values at {string} match {string}")
  public void all_response_values_at_path_match_pattern(String path, String pattern)
      throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path, everyItem(matchesPattern(pattern))));
  }


  @Then("response number value at {string} is {double}")
  public void response_number_value_at_path_is(String path, Double value) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).isNumber());
    result.andExpect(jsonPath(path).value(value));
  }

  @Then("response value at {string} is number")
  public void response_number_value_at_path_is_number(String path) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).isNumber());
  }


  @Then("all response values at {string} are numbers")
  public void all_response_values_at_path_are_numbers(String path) throws Exception {
    // Number is an abstract class, that includes all numeric types
    values_at_path_are_all_instance_of(path, Number.class);
  }

  @Then("response boolean value at {string} is {bool}")
  public void response_boolean_value_at_path_is(String path, Boolean value) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).isBoolean());
    result.andExpect(jsonPath(path).value(value));
  }

  @Then("response array length at {string} is {int}")
  public void response_array_value_at_path_is(String path, Integer length) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).isArray());
    result.andExpect(jsonPath(path, hasSize(length)));
  }

  @Then("response value at {string} is defined")
  public void response_value_at_path_is_defined(String path) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path).exists());
  }

  private void values_at_are_all(String path, Object value) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path, everyItem(isA(value.getClass()))));
    result.andExpect(jsonPath(path, everyItem(is(value))));
  }

  private void values_at_path_are_all_instance_of(String path, Class<?> clazz) throws Exception {
    ResultActions result = world.get(WorldKey.RESPONSE);
    result.andExpect(jsonPath(path, everyItem(isA(clazz))));
  }
}
