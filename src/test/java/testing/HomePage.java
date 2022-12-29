package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class HomePage {

	private static WebDriver webDriver;
	private static String baseUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajdin\\Desktop\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseUrl = "https://trello.com/";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}

	@Test
	void testHeader() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement header = webDriver.findElement(By.xpath("//*[@id=\"skip-target\"]/section[1]/div/div/div[1]/div[1]/div/h1"));
		assertEquals(header.getText(),"Trello brings all your tasks, teammates, and tools together");
	}
	
	@Test
	void testVideo() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;  

		webDriver.get(baseUrl);
		webDriver.manage().window().maximize();
		WebElement watchVideo = webDriver.findElement(By.xpath("//*[@id=\"skip-target\"]/section[1]/div/div/div[1]/span"));
		watchVideo.click();
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		WebElement video = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.w-vulcan--background.w-css-reset")));
		js.executeScript("arguments[0].click()", video);
		WebElement timestamp = webDriver.findElement(By.cssSelector("div.w-playbar__time"));
		wait.until(ExpectedConditions.textToBe(By.cssSelector("div.w-playbar__time"),"0:00"));
		wait.until(ExpectedConditions.textToBe(By.cssSelector("div.w-playbar__time"),"0:10"));
		js.executeScript("arguments[0].click()", video);
		assertEquals(timestamp.getText(),"0:10");
		
		WebElement close = webDriver.findElement(By.xpath("//*[@id=\"modal-7hb3XGCual654XH5qFVSGv\"]/div/div/div/button"));
		close.click();
	}
}
