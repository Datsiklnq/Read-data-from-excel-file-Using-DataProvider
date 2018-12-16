

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject {

	WebDriver driver;

	public PageObject(WebDriver driver) {

		this.driver = driver;
	}

	By firstName = By.xpath("//*[@id='u_0_c']");
	By lastName = By.xpath("//*[@id='u_0_e']");
	By mobilePhone = By.xpath("//*[@id='u_0_h']");
	By password = By.xpath("//input[@id='u_0_o']");
	By submit = By.id("u_0_u");
	By loginEmailBox = By.xpath("//input[@id='email']");
	By loginPasswordBox = By.xpath("//input[@id='pass']");
	By button = By.xpath("//input[@id='u_0_9']");
	By confirmAge = By.xpath("//button[@type='submit']");
	By error = By.xpath("//*[@class='_5v-0 _53im']");
	
	public WebElement firstNameBox() {

		return driver.findElement(firstName);
	}

	public WebElement lastNameBox() {
		return driver.findElement(lastName);
	}

	public WebElement mobilePhoneBox() {
		return driver.findElement(mobilePhone);
	}

	public WebElement passwordBox() {
		return driver.findElement(password);
	}

	public WebElement signUp() {
		return driver.findElement(submit);
	}

	public WebElement loginEmailBox() {
		return driver.findElement(loginEmailBox);
	}

	public WebElement loginPasswordBox() {

		return driver.findElement(loginPasswordBox);
	}

	public WebElement radioButton() {
		return driver.findElement(button);
	}

	public WebElement confirm() {

		return driver.findElement(confirmAge);
	}

	public WebElement error(){
		
		return driver.findElement(error);
	}
	public void verifyTextControl() {

		// Text Control are empty on load 
		// return true if all expressions are true
		if (driver.findElement(firstName).getAttribute("value").isEmpty()
				&& driver.findElement(lastName).getAttribute("value").isEmpty()
				&& driver.findElement(mobilePhone).getAttribute("value").isEmpty()
				&& driver.findElement(password).getAttribute("value").isEmpty()) {

			System.out.println("Text Controls are Empty on load");
		} else {
			System.out.println("Text Controls are filled out ");
		}

	}

}