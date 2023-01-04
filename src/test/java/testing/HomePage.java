package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.*;
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
	void TestHeader() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement header = webDriver.findElement(By.xpath("//*[@id=\"skip-target\"]/section[1]/div/div/div[1]/div[1]/div/h1"));
		assertEquals(header.getText(),"Trello brings all your tasks, teammates, and tools together");
	}
	

	@Test
	void TestVideo() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;  

		webDriver.get(baseUrl);
		//webDriver.manage().window().maximize();
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
	@Test
	void testSlides() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement firstSlideButton  = webDriver.findElement(By.xpath("//*[@id=\"skip-target\"]/div[1]/section/div/div/div/div/div[1]/div/div/button[1]"));
		WebElement secondSlideButton = webDriver.findElement(By.xpath("//*[@id=\"skip-target\"]/div[1]/section/div/div/div/div/div[1]/div/div/button[2]"));
		WebElement thirdSlideButton = webDriver.findElement(By.xpath("//*[@id=\"skip-target\"]/div[1]/section/div/div/div/div/div[1]/div/div/button[3]"));
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
		if(!secondSlideButton.isDisplayed())
		{
			System.out.println("Dekstop version for slide 2 failed trying mobile button");
			secondSlideButton = webDriver.findElement(By.xpath("/html/body/div[1]/main/div[1]/section/div/div/div/div/div[3]/div/div[2]/div/button[2]"));
		}
		secondSlideButton.click();
		wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"skip-target\"]/div[1]/section/div/div/div/div/div[2]/div/div/div/div[2]"), "aria-hidden", "false"));
		
		if(!firstSlideButton.isDisplayed())
		{
			System.out.println("Dekstop version for slide 1 failed trying mobile button");
			firstSlideButton = webDriver.findElement(By.xpath("/html/body/div[1]/main/div[1]/section/div/div/div/div/div[3]/div/div[2]/div/button[1]"));
		}
		firstSlideButton.click();
		wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"skip-target\"]/div[1]/section/div/div/div/div/div[2]/div/div/div/div[1]"), "aria-hidden", "false"));
		
		if(!thirdSlideButton.isDisplayed())
		{
			System.out.println("Dekstop version for slide 3 failed trying mobile button");
			thirdSlideButton = webDriver.findElement(By.xpath("/html/body/div[1]/main/div[1]/section/div/div/div/div/div[3]/div/div[2]/div/button[3]"));
		}
		thirdSlideButton.click();
		wait.until(ExpectedConditions.attributeContains(By.xpath("//*[@id=\"skip-target\"]/div[1]/section/div/div/div/div/div[2]/div/div/div/div[3]"), "aria-hidden", "false"));
	}
	@Test
	void TestDragSlider() throws InterruptedException {
		webDriver.get(baseUrl);
		Actions builder = new Actions(webDriver);
		WebElement DragSliderFirstElement = webDriver.findElement(By.xpath("/html/body/div[1]/main/section[2]/div[2]/div/div/div[1]"));
		builder.clickAndHold(DragSliderFirstElement).moveByOffset(-200, 0).perform();
		WebElement DragSlider = webDriver.findElement(By.xpath("/html/body/div[1]/main/section[2]/div[2]/div/div"));
		assertEquals(DragSlider.getCssValue("margin-left"), "-216px");
		builder.clickAndHold(DragSliderFirstElement).moveByOffset(200, 0).perform();
		assertEquals(DragSlider.getCssValue("margin-left"), "13.6667px");
	}
	
}
