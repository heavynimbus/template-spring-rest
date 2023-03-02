package heavynimbus.templatespringrest.stepDefs;

import io.cucumber.java.Before;

public class Hooks {

  @Before
  public void initializeScenario() {
    System.out.println("Scenario initialized");
  }
}
