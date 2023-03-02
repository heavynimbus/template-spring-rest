package com.junia.jeeproject.stepDefs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.JsonNode;
import com.junia.jeeproject.world.World;
import com.junia.jeeproject.world.WorldKey;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RequiredArgsConstructor
public class RequestStepDefs {

  private final World world;
  private final MockMvc mockMvc;

  @Given("request headers are:")
  public void request_headers_are(DataTable dataTable) {
    Map<String, String> headersMap = dataTable.asMap(String.class, String.class);
    HttpHeaders headers = new HttpHeaders();
    headersMap.forEach(headers::add);
    world.put(WorldKey.REQUEST_HEADERS, headers);
  }

  @Given("request body is:")
  public void request_body_is(JsonNode requestBody) {
    world.put(WorldKey.REQUEST_BODY, requestBody);
  }

  @When("I send a {httpMethod} request to {uri}")
  public void i_send_a_request_to(HttpMethod httpMethod, String uri) throws Exception {
    HttpHeaders headers = world.get(WorldKey.REQUEST_HEADERS);
    JsonNode body = world.get(WorldKey.REQUEST_BODY);
    MockHttpServletRequestBuilder req = request(httpMethod, uri);
    if (headers != null) req = req.headers(headers);
    if (body != null) req = req.content(world.get(WorldKey.REQUEST_BODY).toString());
    ResultActions result = mockMvc.perform(req)
        .andDo(print());
    world.put(WorldKey.RESPONSE, result);
    world.remove(WorldKey.REQUEST_HEADERS);
    world.remove(WorldKey.REQUEST_BODY);
  }
}
