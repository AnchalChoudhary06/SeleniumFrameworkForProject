package pageEvents;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageObjects.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {
	ElementFetch ele = new ElementFetch();

	public void verifyIfLogInPageIsLoaded() {
		WebElement loginText = ele.getWeElement("XPATH", LoginPageElements.LoginButtonText);
		String Login_Button_text= loginText.getText();
		Assert.assertEquals(Login_Button_text, "Login");
	}
	
	public void enterCredentials() {
		WebElement emailText = ele.getWeElement("XPATH", LoginPageElements.emailAddress);
		emailText.sendKeys("anchal@gmail.com");
		WebElement password = ele.getWeElement("XPATH", LoginPageElements.passwordField);
		password.sendKeys("a6757657");
	}
	
}
