package zdtestpol51bdd.devto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.awt.*;
import java.util.List;

public class DevToStepsDefinitions<webdriver> {
    WebDriver driver;
    WebDriverWait wait;
    WebElement sendKeys;
    String firstBlogTitle;
    String firstCastTitle;
    String searchingPhrase;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
    }
    @Given("I go to devto mainpage")
    public void i_go_to_devto_mainpage()  {
        driver.get("https://dev.to");
    }
    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
        WebElement firstBlog = driver.findElement(By.cssSelector("h2.crayons-story__title > a"));
        firstBlogTitle = firstBlog.getText();
        firstBlog.click();
    }
    @Then("I should be redirected to blog page")
    public void i_should_be_redirected_to_blog_page() {
        wait.until(ExpectedConditions.titleContains(firstBlogTitle));
        WebElement blogTitle = driver.findElement(By.tagName("h1"));
        String blogTitleText = blogTitle.getText();
        Assert.assertEquals(firstBlogTitle, blogTitleText);
    }
    @When("I click text podcast in main page")
    public void i_click_text_podcast_in_main_page() {
        WebElement podcast = driver.findElement(By.linkText("Podcasts"));
        podcast.click();
    }
    @When("I click on first cast displayed")
    public void i_click_on_first_cast_displayed() {
        wait.until(ExpectedConditions.titleContains("Podcasts"));
        WebElement firstCast = driver.findElement(By.tagName("h3"));
        firstCastTitle = firstCast.getText();
        firstCastTitle = firstCastTitle.replace("podcast","");
        firstCast.click();
    }
    @Then("I should be redirected to cast site")
    public void i_should_be_redirected_to_cast_site(){
        wait.until(ExpectedConditions.titleContains(firstCastTitle));
        WebElement castTitle = driver.findElement(By.tagName("h1"));
        String castTitleText = castTitle.getText();
        Assert.assertEquals(firstCastTitle, castTitleText);
    }

    @When("I search for {string} phrase")
    public void i_search_for_phrase(String phrase) {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(phrase);
        searchingPhrase = phrase;
        searchBar.sendKeys(Keys.RETURN);
    }
    @Then("Top {int} blogs found should have correct phrase in title")
    public void top_blogs_found_should_have_correct_phrase_in_title(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.crayons-story__title"))); //h3
        wait.until(ExpectedConditions.attributeContains(By.id("substories"),"class","search-results-loaded"));
        List<WebElement> allPosts = driver.findElements(By.cssSelector(".crayons-story__title > a")); // a
        for (int i=0;i<3;i++){
            WebElement singlePost = allPosts.get(i);
            String singlePostTitle = singlePost.getText(); // a wyciagaj text
            System.out.println(singlePostTitle); //print
            Boolean isTestingInTitle = singlePostTitle.contains(searchingPhrase);
            Assert.assertTrue(isTestingInTitle);
        }
    }
    }

