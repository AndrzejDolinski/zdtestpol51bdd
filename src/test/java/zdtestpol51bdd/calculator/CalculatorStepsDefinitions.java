package zdtestpol51bdd.calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CalculatorStepsDefinitions {

    Calculator calculator=new Calculator();
    Integer result;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        Calculator calculator=new Calculator();
    }
    @When("I add {int} and {int}")
    public void i_add_and(Integer int1, Integer int2) {
        result =calculator.addTwoNumbers(int1, int2);
    }
    @When("I subtract {int} from {int}")
    public void i_subtract_from(Integer int1, Integer int2) {
        result = calculator.subtractTwoNumbers(int1, int2);
    }
    @When("I divide {int} by {int}")
    public void i_devide_by(Integer int1, Integer int2) {
        result = calculator.divideTwoNumbers(int1, int2);
    }
    @When("I multiply {int} times {int}")
    public void i_multiply_times(Integer int1, Integer int2) {
        result =calculator.multiplyTwoNumbers(int1,int2);
    }
    @Then("I should get {int}")
    public void i_should_get(Integer int1) {
        Assert.assertEquals(result, int1);
    }
}
