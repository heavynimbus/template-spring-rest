package heavynimbus.templatespringrest.config;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.NonNull;
import org.junit.ClassRule;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@CucumberContextConfiguration
@ContextConfiguration(initializers = CucumberTestConfiguration.DockerPostgresDataSourceInitializer.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CucumberTestConfiguration {

  @ClassRule
  public static final PostgreSQLContainer<?> postgreSQLContainer;

  static {
    postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.2-alpine");
    postgreSQLContainer.start();
  }

  public static class DockerPostgresDataSourceInitializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {
      TestPropertyValues.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
              "spring.datasource.username=" + postgreSQLContainer.getUsername(),
              "spring.datasource.password=" + postgreSQLContainer.getPassword())
          .applyTo(applicationContext);
    }
  }
}
