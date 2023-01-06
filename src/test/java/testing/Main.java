package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.Alphanumeric;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(Alphanumeric.class)
class Main {


	private static WebDriver webDriver;
	private static String baseUrl;
	private static String loginEmail = "trellotestingajdin@gmail.com";
	private static String loginPassword = "Ajdin123";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajdin\\Desktop\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseUrl = "https://trello.com/login";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	
	@Test
	@Order(1)
	void TestHeader() throws InterruptedException {
		webDriver.get(baseUrl);
		//webDriver.manage().window().maximize();
		WebElement header = webDriver.findElement(By.xpath("/html/body/div[1]/section/div/div/h1"));
		assertEquals(header.getText(),"Log in to Trello");
	}
	
	
	@Test
	@Order(2)
	void TestLogin() throws InterruptedException { 
		webDriver.get(baseUrl);
		WebElement emailField = webDriver.findElement(By.xpath("//*[@id=\"user\"]"));
		emailField.sendKeys(loginEmail);
		WebElement continueButton = webDriver.findElement(By.xpath("//*[@id=\"login\"]"));
		continueButton.click();
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]")));
		passwordField.sendKeys(loginPassword);
		WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"login-submit\"]"));
		loginButton.click();
	}
	
	@Test
	@Order(3)
	void CreateNewBoard() throws InterruptedException { 
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		WebElement boardButton =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/button")));
		boardButton.click();
		WebElement newBoardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/section/div/div/nav/ul/li[1]/button")));
		newBoardButton.click();
		WebElement boardName = webDriver.findElement(By.xpath("/html/body/div[3]/div/section/div/form/div[1]/label/input"));
		boardName.sendKeys("Test Board");
		WebElement createButton = webDriver.findElement(By.xpath("/html/body/div[3]/div/section/div/form/button"));
		createButton.click();
	}
	@Test
	@Order(4)
	void TestBoard() throws InterruptedException { 
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		WebElement boardButton =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/div[2]/div/div[2]/button")));
		boardButton.click();
		WebElement newBoardButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/section/div/div/nav/ul/li[1]/button")));
		newBoardButton.click();
		WebElement boardName = webDriver.findElement(By.xpath("/html/body/div[3]/div/section/div/form/div[1]/label/input"));
		boardName.sendKeys("Test Board");
		WebElement createButton = webDriver.findElement(By.xpath("/html/body/div[3]/div/section/div/form/button"));
		createButton.click();
	}
}
