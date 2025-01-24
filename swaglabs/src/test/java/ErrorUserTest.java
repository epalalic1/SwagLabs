import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/errorUser.feature",
        glue = "steps"
)

public class ErrorUserTest {
    
}
