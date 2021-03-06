package zdtestpol51bdd.zadanie1;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie1StepsDefinitions <webdriver> {
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        driver=new ChromeDriver();
    }
    @Given("enter mainpage")
    public void enter_mainpage() {
        driver.get("https://dev.to/");
    }
    @When("I click on Sign In button")
    public void i_click_on_sign_in_button() {
        driver.findElement(By.cssSelector("crayons-link crayons-link--block fw-bold")).click();
    }
    @Then("I should be redirected to login page")
    public void i_should_be_redirected_to_login_page(){

    }
}
