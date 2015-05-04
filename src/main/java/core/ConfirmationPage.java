package core;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage {
WebDriver driver;

@FindBy(id="id_back_button")
@CacheLookup
static WebElement back_but;

public ConfirmationPage(WebDriver wd) {
		driver = wd;
		}
	//method for back button verification
	 public void verifyBackbutton(String title_page, String baseUrl){
		 driver.get(baseUrl);
		 back_but.click();
		 assertEquals(driver.getTitle(), title_page);
		 }
	
}
