package zdtestpol51bdd.devto;
import io.cucumber.java.After;
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
import zdtestpol51.browserUtills.BaseDriver;
import zdtestpol51bdd.devto.pages.MainPage;
import zdtestpol51bdd.devto.pages.PodcastListPage;
import zdtestpol51bdd.devto.pages.SingleBlogPage;
import zdtestpol51bdd.devto.pages.SinglePodcastPage;


import java.util.List;

public class DevToStepsDefinitions<webdriver> {
    WebDriver driver;
    WebDriverWait wait;
    WebElement sendKeys;
    String firstBlogTitle;
    String firstCastTitle;
    String searchingPhrase;
    MainPage mainPage;
    SingleBlogPage singleBlogPage;
    PodcastListPage podcastListPage;
    SinglePodcastPage singlePodcastPage;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = BaseDriver.setHedlessDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @Given("I go to devto mainpage")
    public void i_go_to_devto_mainpage() {
//        driver.get("https://dev.to");
      mainPage = new MainPage (driver);

    }

    @When("I click on first blog displayed")
    public void i_click_on_first_blog_displayed() {
        firstBlogTitle = mainPage.firstBlog.getText();
        singleBlogPage = mainPage.selectFirstBlog();
    }


    @Then("I should be redirected to blog page")
    public void i_should_be_redirected_to_blog_page() {
        wait.until(ExpectedConditions.titleContains(firstBlogTitle));
//        WebElement blogTitle = driver.findElement(By.tagName("h1"));
        singleBlogPage = new SingleBlogPage(driver);
        String blogTitleText = singleBlogPage.blogTitle.getText();
        Assert.assertEquals(firstBlogTitle, blogTitleText);
    }

    @When("I go to podcast section")
    public void i_go_to_podcast_section() {
//        WebElement podcast = driver.findElement(By.linkText("Podcasts"));
        mainPage.goToPodcastSection();
    }

    @When("I click on first podcast on the list")
    public void i_click_on_first_podcast_on_the_list() {
        wait.until(ExpectedConditions.titleContains("Podcasts"));
//        WebElement firstCast = driver.findElement(By.tagName("h3"));
        podcastListPage = new PodcastListPage(driver);
        firstCastTitle = podcastListPage.firstCast.getText();
        firstCastTitle = firstCastTitle.replace("podcast", "");
        podcastListPage.selectFirstPodcast();
    }

    @When("I play the podcast")
    public void i_play_the_podcast() {
        // Write code here that turns the phrase above into concrete actions
        wait.until(ExpectedConditions.titleContains(firstCastTitle));
//        WebElement playButton = driver.findElement(By.id("record"));
        singlePodcastPage=new SinglePodcastPage(driver);
        singlePodcastPage.playThePodcast();


    }
    @Then("Podcast Should be played")
    public void podcast_should_be_played() {
//        WebElement initializing = driver.findElement(By.className("status-message"));
        wait.until(ExpectedConditions.invisibilityOf(singlePodcastPage.initializing));
//        WebElement pauseBtn = driver.findElement(By.xpath("//img[contains(@class,'pause-butt')]"));
        Boolean isPauseBtnVisible = singlePodcastPage.pauseBtn.isDisplayed();
        Assert.assertTrue(isPauseBtnVisible);
    }


        //@Then("I should be redirected to cast site")
        //public void i_should_be_redirected_to_cast_site() {
        //wait.until(ExpectedConditions.titleContains(firstCastTitle));
        //WebElement castTitle = driver.findElement(By.tagName("h1"));
        //String castTitleText = castTitle.getText();
        //Assert.assertEquals(firstCastTitle, castTitleText);
        //}

        @When("I search for {string} phrase")
        public void i_search_for_phrase (String phrase){
            WebElement searchBar = driver.findElement(By.name("q"));
            searchBar.sendKeys(phrase);
            searchingPhrase = phrase;
            searchBar.sendKeys(Keys.RETURN);
        }


        @Then("Top {int} blogs found should have correct phrase in title or snippet")
        public void top_blogs_found_should_have_correct_phrase_in_title (Integer int1){
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3.crayons-story__title"))); //h3
            wait.until(ExpectedConditions.attributeContains(By.id("substories"), "class", "search-results-loaded"));
            List<WebElement> allPosts = driver.findElements(By.className("crayons-story__body")); // div - caly wpis
            if (allPosts.size() >= int1) {
                for (int i = 0; i < int1; i++) {
                    WebElement singlePost = allPosts.get(i);
                    WebElement singlePostTitle = singlePost.findElement(By.cssSelector(".crayons-story__title > a")); //tytul kafelka
                    String singlePostTitleText = singlePostTitle.getText().toLowerCase(); // wyciagnij tekst z tytulu
                    Boolean isPhraseInTitle = singlePostTitleText.contains(searchingPhrase);
                    if (isPhraseInTitle) { // isPhraseInTitle == true
                        Assert.assertTrue(isPhraseInTitle);
                    } else {
                        WebElement snippet = singlePost.findElement(By.xpath("//div[contains(@class,'crayons-story__snippet')]"));
                        String snippetText = snippet.getText().toLowerCase();
                        Boolean isPhraseInSnippet = snippetText.contains(searchingPhrase);
                        Assert.assertTrue(isPhraseInSnippet);
                    }
                }
            }
        }
        @After
        public void tearDown() {
        driver.quit();
        }

    }

