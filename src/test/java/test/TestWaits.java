package test;

import java.time.Duration;
import java.util.function.Function;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class TestWaits {

	WebDriver driver;
	String PATH_DRIVER = "./src/test/resources/Driver/chromedriver.exe";
	String TIPO_DRIVER = "webdriver.chrome.driver";
	String URL = "https://the-internet.herokuapp.com/dynamic_loading/2";

	@Before
	public void setUp() {

		System.setProperty(TIPO_DRIVER, PATH_DRIVER);
		driver = new ChromeDriver();
		driver.get(URL);
		// ESPERA IMPLICITA
		// driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

	}

	// @Test
	public void testsinWait() {
		/*
		 * WebElement btnStart =
		 * driver.findElement(By.xpath("//*[@id='start']/button")); btnStart.click();
		 * WebElement txtSaludo = driver.findElement(By.xpath("//*[@id='finish']/h4"));
		 * Assert.assertEquals("hello World!", txtSaludo.getText());
		 */

	}

	// @Test //USANDO WebDriverWait
	public void testconWaitExplicit() {
		/*
		 * By localizadorTxtSaludo = By.xpath("//*[@id='finish']/h4"); WebElement
		 * btnStart = driver.findElement(By.xpath("//*[@id='start']/button"));
		 * btnStart.click(); WebDriverWait esperaExplicita = new WebDriverWait(driver,
		 * 6); esperaExplicita.until(ExpectedConditions.visibilityOfElementLocated(
		 * localizadorTxtSaludo));
		 * 
		 * WebElement txtSaludo = driver.findElement(By.xpath("//*[@id='finish']/h4"));
		 * Assert.assertEquals("hello World!", txtSaludo.getText());
		 */

	}

	@Test
	public void testwitchWaitExplicitFluent() {
		final By localizadorTxtSaludo = By.xpath("//*[@id='finish']/h4");
		WebElement txtSaludo = null;

		WebElement btnStart = driver.findElement(By.xpath("//*[@id='start']/button"));
		btnStart.click();

		// ESPERA FLUENTWAIT
		Wait<WebDriver> esperaFluida = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
		esperaFluida.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(localizadorTxtSaludo);
			}
		});

		// PASO 2 : VALIDAR EL TEXTO
		txtSaludo = driver.findElement(localizadorTxtSaludo);
		Assert.assertEquals("Hello World!", txtSaludo.getText());

	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
