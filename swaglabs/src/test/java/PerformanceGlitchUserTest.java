import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources/performanceGlitchUser.feature",
        glue = "steps"
)



public class PerformanceGlitchUserTest {
    
}