package zdtestpol51bdd.calculator;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/zdtestpol51bdd.calculator/Calculator.feature"
)
public class CalculatorTestRunner {
}
