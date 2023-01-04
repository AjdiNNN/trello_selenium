package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Random;

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

class Register {

	private static WebDriver webDriver;
	private static String baseUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajdin\\Desktop\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseUrl = "https://trello.com/signup";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	@Test
	void TestHeader() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement header = webDriver.findElement(By.xpath("//*[@id=\"signup-password\"]/h1"));
		assertEquals(header.getText(),"Sign up for your account");
	}
	@Test
	void TestRegister() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement inputEmail = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
		inputEmail.sendKeys(getRandomMail());
		Thread.sleep(2000);
	}
	protected String getRandomMail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr+"@test.com";

    }
}
