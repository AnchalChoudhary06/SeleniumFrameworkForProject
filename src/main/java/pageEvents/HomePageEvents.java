package pageEvents;

import pageObjects.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
	ElementFetch ele = new ElementFetch();
	public void signInButton() {
		ele.getWeElement("XPATH", HomePageElements.signInButtonText).click();
	}
	
}
