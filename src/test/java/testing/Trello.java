package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Trello {


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
		webDriver.manage().window().maximize();
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
		WebElement board =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[1]/div[1]/div[1]/h1")));
		assertEquals(board.getText(),"Test Board");
	}
	
	@Test
	@Order(4)
	void MakeNewSwimLanes() throws InterruptedException { 
		JavascriptExecutor js = (JavascriptExecutor) webDriver;  
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		WebElement addCard =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div/form/a")));
		js.executeScript("arguments[0].click()", addCard);
		WebElement cardTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div/form/input")));
		cardTitle.sendKeys("This is test");
		WebElement card = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div/form/div/input")));
		card.click();
		WebElement addCard2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div/form/a")));
		js.executeScript("arguments[0].click()", addCard2);
		WebElement cardTitle2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div/form/input")));
		cardTitle2.sendKeys("This is test2");
		WebElement card2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div/form/div/input")));
		card2.click();
		WebElement testName = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[1]/textarea"));
		assertEquals(testName.getText(), "This is test");
	}
	
	@Test
	@Order(5)
	void MakeCard() throws InterruptedException { 
		WebElement addCard = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[3]/a"));
		if(addCard.isDisplayed())
			addCard.click();
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
		WebElement cardName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#board > div.js-list.list-wrapper > div > div.list-cards.u-fancy-scrollbar.u-clearfix.js-list-cards.js-sortable.ui-sortable > div > div.list-card.js-composer > div > textarea")));
		cardName.sendKeys("Test card");
		WebElement addCardConfirm = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/div/div[2]/div[1]/input"));
		addCardConfirm.click();
		WebElement cardName2 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#board > div.js-list.list-wrapper > div > div.list-cards.u-fancy-scrollbar.u-clearfix.js-list-cards.js-sortable.ui-sortable > div > div.list-card.js-composer > div > textarea")));
		cardName2.sendKeys("Test card 2");
		WebElement addCardConfirm2 = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/div/div[2]/div[1]/input"));
		addCardConfirm2.click();
		WebElement cardName3 =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#board > div.js-list.list-wrapper > div > div.list-cards.u-fancy-scrollbar.u-clearfix.js-list-cards.js-sortable.ui-sortable > div > div.list-card.js-composer > div > textarea")));
		cardName3.sendKeys("Test card 3");
		WebElement addCardConfirm3 = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/div/div[2]/div[1]/input"));
		addCardConfirm3.click();
	}
	
	@Test
	@Order(6)
	void EnterCard() throws InterruptedException { 
		WebElement card = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/a/div[3]"));
		card.click();
		
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		WebElement cardDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div")));
		assertTrue(cardDisplay.isDisplayed());
	}
	
	@Test
	@Order(7)
	void ModifyCardsDescription() throws InterruptedException { 
	    try {
	    	Thread.sleep(2500);
	    	WebElement enterDescr = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]"
	    			+ "/div[2]/div/div/div/div[2]/div/div/p[1]/a"));
	    	JavascriptExecutor js = (JavascriptExecutor) webDriver; 
			js.executeScript("arguments[0].click()", enterDescr);
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        System.out.println("Description already clicked");
	    }
		WebElement description = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]"
				+ "/div[2]/div/div/div/div[2]/div/div/div[3]/textarea"));
		description.sendKeys("This is test description");
		WebElement save = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]"
				+ "/div[2]/div/div/div/div[2]/div/div/div[3]/div/input[1]"));
		save.click();
		WebElement descriptionText = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]"
				+ "/div[2]/div/div/div/div[2]/div/div/div[2]/p"));
		assertEquals(descriptionText.getText(), "This is test description");
	}
	
	@Test
	@Order(8)
	void MakeAComment() throws InterruptedException { 
		WebElement comment = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[2]/form/div/div/textarea"));
		comment.sendKeys("This is test comment yaay");
		webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[11]/div[2]/form/div/div/div[1]/input")).click();
		
		WebElement commentTest = webDriver.findElement(By.cssSelector("#chrome-container > div.window-overlay > div > div > div > div.window-main-col > "
				+ "div:nth-child(11) > div.js-list-actions.mod-card-back > "
				+ "div > div.phenom-desc > div.comment-container > div > div.current-comment.js-friendly-links.js-open-card > p"));
		assertEquals(commentTest.getText(), "This is test comment yaay");
	}
	
	@Test
	@Order(9)
	void MakeALabel() throws InterruptedException { 
		WebElement label = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[5]/div[2]/div/a[2]"));
		label.click();
		WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(5));
		WebElement orange = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.atlaskit-portal-container > div > "
				+ "section > div > div > ul > li:nth-child(3) > label > input")));
		JavascriptExecutor js = (JavascriptExecutor) webDriver;  
		js.executeScript("arguments[0].click()", orange);
		WebElement exit = webDriver.findElement(By.cssSelector("body > div.atlaskit-portal-container > div > section > header > button"));
		exit.click();
		WebElement labelShown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/a[1]/div[3]/"
				+ "div[1]/div/div/div/button")));
		assertTrue(labelShown.isDisplayed());
	}
	
	@Test
	@Order(10)
	void SetADue() throws InterruptedException { 
		WebElement dates = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[5]/div[2]/div/div[1]/div/button"));
		dates.click();
		WebDriverWait wait = new WebDriverWait(webDriver,Duration.ofSeconds(5));
		WebElement date31 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.atlaskit-portal-container > div > section "
				+ "> div > div > form > div.i75OBvhqAf7FKJ > div > div > div.css-1in5egx > div:nth-child(2) > div:nth-child(5) > button:nth-child(3)")));
		date31.click();
		WebElement save = webDriver.findElement(By.cssSelector("div.\\+13g83nVXr479o > button.Ts\\+YceGnvTbKoG.HPCwi137Em5EYI.ZP\\+x7L4IomBdCS.JIXQq8gDYY04N6"));
		save.click();
		Thread.sleep(500);
		WebElement dueDate = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/div/div[4]/div[1]/div[6]/h3"));
		assertTrue(dueDate.isDisplayed());
	}
	
	@Test
	@Order(11)
	void ExitCard() throws InterruptedException { 
		WebElement exitCard = webDriver.findElement(By.xpath("//*[@id=\"chrome-container\"]/div[3]/div/div/a"));
		exitCard.click();
	}
	
	@Test
	@Order(12)
	void MoveCard() throws InterruptedException { 
		Actions builder = new Actions(webDriver);
		WebElement card = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/a/div[3]"));
		WebElement swimlane = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[2]"));
		builder.dragAndDrop(card, swimlane);
		builder.perform();
		WebElement movedCardText = webDriver.findElement(By.xpath("//*[@id=\"board\"]/div[2]/div/div[2]/a/div[3]/span"));
		assertEquals(movedCardText.getText(),"Test card");
	}

	@Test
	@Order(13)
	void RemoveBoard() throws InterruptedException { 
		Actions builder = new Actions(webDriver);
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
		WebElement expand =  webDriver.findElement(By.xpath("//*[@id=\"popover-boundary\"]/div/div[1]/nav/div[2]/div/button"));
		if(expand.isDisplayed())
			expand.click();
		WebElement hover = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"popover-boundary\"]/div/div[1]/nav/div[1]"
				+ "/div/div/div[2]/div/div[3]/ul/div[2]/li/a")));
		builder.moveToElement(hover);
		builder.build().perform();
		WebElement dots = webDriver.findElement(By.xpath("//*[@id=\"popover-boundary\"]/div/div[1]/nav/div[1]/div/div/div[2]/div/div[3]/ul/div[2]/li[1]"
				+ "/div[2]/div[1]/button"));
		dots.click();
		WebElement close = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.atlaskit-portal-container > div > section > div "
				+ "> div > button")));
		close.click();
		WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.atlaskit-portal-container > div "
				+ "> section > div > button")));
		confirm.click();
		WebElement closed =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ddfwq6CerXG2na")));
		assertTrue(closed.isDisplayed());
	}
}

