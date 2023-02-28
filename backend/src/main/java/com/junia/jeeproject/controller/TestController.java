package com.junia.jeeproject.controller;

import com.junia.jeeproject.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
  private final UserRepository userRepository;


  @GetMapping
  public TestDto test() {
    return new TestDto("test");
  }
}
