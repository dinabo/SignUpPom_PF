package core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage {

	WebDriver driver;
	
	@FindBy(id="id_img_facebook")
	@CacheLookup
	static WebElement facebook_icon;
	
	@FindBy(id="id_fname")
	@CacheLookup
	static WebElement fname_field;
	
	@FindBy(id="id_lname")
	@CacheLookup
	static WebElement lname_field;
	
	@FindBy(id="id_email")
	@CacheLookup
	static WebElement email_field;
	
	@FindBy(id="id_phone")
	@CacheLookup
	static WebElement phone_field;
	
	@FindBy(id="id_g_radio_01")
	@CacheLookup
	static WebElement radio_but_01;
	
	@FindBy(id="id_g_radio_02")
	@CacheLookup
	static WebElement radio_but_02;
	
	@FindBy(id="id_checkbox")
	@CacheLookup
	static WebElement checkbox;
	
	@FindBy(id="id_state")
	@CacheLookup
	static WebElement state_field;
	
	@FindBy(id="id_submit_button")
	@CacheLookup
	static WebElement submit_but;
	
	@FindBy(id="id_fname_conf")
	@CacheLookup
	static WebElement fname_conf;
	
	@FindBy(id="id_lname_conf")
	@CacheLookup
	static WebElement lname_conf;
	
	@FindBy(id="id_email_conf")
	@CacheLookup
	static WebElement email_conf;

	@FindBy(id="id_phone_conf")
	@CacheLookup
	static WebElement phone_conf;
	
	@FindBy(id="id_gender_conf")
	@CacheLookup
	static WebElement gender_conf;
	
	@FindBy(id="id_state_conf")
	@CacheLookup
	static WebElement state_conf;
	
	@FindBy(id="id_terms_conf")
	@CacheLookup
	static WebElement terms_conf;
	
	@FindBy(id="id_current_location")
	@CacheLookup
	static WebElement current_location;
	
	@FindBy(id="ErrorLine")
	@CacheLookup
	static WebElement ErrorLine;
	
	
	
	public SignUpPage(WebDriver wd) {
		driver = wd;
		}
	
	
	
	//method for title verification
	public void verify_title(String expected_title, String baseUrl){
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String actual_title = driver.getTitle();
		assertEquals(expected_title, actual_title);
	}
	
	//method for facebook link verification
	public void verify_link(String title_facebook_expected){
		PageFactory.initElements(driver, SignUpPage.class);
		facebook_icon.click();
		//driver.findElement(By.id("id_img_facebook")).click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		ArrayList<String> allTabs = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(allTabs.get(1));
		String actual_title = driver.getTitle();
		driver.close();
		driver.switchTo().window(allTabs.get(0));
		assertEquals(title_facebook_expected, actual_title);
	} 
	
	//method for error-handling verification
	public void verify_error_handling(String error_expected ){
		submit_but.click();
		String error_actual = ErrorLine.getText();
		assertEquals(error_expected, error_actual);
		}
	
	//method for submit form verification
	public void verify_submit_form(String fname, String lname, String email, 
			String phone, String gender, String state, Boolean terms, String cterms, String confTitle){
		fname_field.clear();
		fname_field.sendKeys(fname);
		lname_field.clear();
		lname_field.sendKeys(lname);
		email_field.clear();
		email_field.sendKeys(email);
		phone_field.clear();
		phone_field.sendKeys(phone);
		if (gender.equalsIgnoreCase("male")){
			radio_but_01.click();
			}else if(gender.equalsIgnoreCase("female")){
				radio_but_02.click();
			}
			if (terms == true){
				checkbox.click();
			}
			if (state.isEmpty()){
			}else{
			new Select(state_field).selectByVisibleText(state);
			}
			submit_but.click();
		
		String fname_conf_actual = fname_conf.getText();
		String lname_conf_actual = lname_conf.getText();
		String email_conf_actual = email_conf.getText();
		String phone_conf_actual = phone_conf.getText();
		String gender_conf_actual = gender_conf.getText();
		String state_conf_actual = state_conf.getText();
		String terms_conf_actual = terms_conf.getText();
		
		assertEquals(driver.getTitle(), confTitle);
		assertEquals(fname, fname_conf_actual);
		assertEquals(lname, lname_conf_actual);
		assertEquals(email, email_conf_actual);
		assertEquals(phone, phone_conf_actual);
		assertEquals(gender, gender_conf_actual);
		assertEquals(state, state_conf_actual);
		assertEquals(cterms, terms_conf_actual);
	}
	
	//method for content verification
	public void verifyContent(String baseUrl, String currentCity){
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String currentCity_actual = current_location.getText();	
		assertEquals(currentCity_actual, currentCity);
	}
}


