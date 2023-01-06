package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class Register {

	private static WebDriver webDriver;
	private static String baseUrl;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajdin\\Desktop\\chromedriver.exe");
		webDriver = new ChromeDriver();
		baseUrl = "https://id.atlassian.com/signup";
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		webDriver.quit();
	}
	@Test
	void TestHeader() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement header = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/div[2]/div/section/div[1]/h5"));
		assertEquals(header.getText(),"Sign up for your account");
	}
	@Test
	void TestRegister() throws InterruptedException {
		webDriver.get(baseUrl);
		WebElement inputEmail = webDriver.findElement(By.xpath("//*[@id=\"email\"]"));
		inputEmail.sendKeys(getRandomMail());
		WebElement submit = webDriver.findElement(By.xpath("//*[@id=\"signup-submit\"]"));
		submit.click();
		/*
		 * Captcha
		 */
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div[2]/iframe")));
		assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email-sent-page\"]/div[2]/span"))).getText(),
				"To complete setup and log in, click the verification link in the email we’ve sent to");
		WebElement resendVerification = webDriver.findElement(By.xpath("//*[@id=\"welcome-send-validation-submit\"]/span"));
		resendVerification.click();
		assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/div/div/div/div/div[1]/div/span[2]"))).getText(),
				"We've sent you an email!");
		
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
