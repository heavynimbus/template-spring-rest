package com.junia.jeeproject.stepDefs;

import io.cucumber.java.Before;

public class Hooks {

  @Before
  public void initializeScenario() {
    System.out.println("Scenario initialized");
  }
}
