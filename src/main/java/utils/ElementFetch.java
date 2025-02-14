package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class ElementFetch {
	public WebElement getWeElement(String indentifierType, String inentifierValue) {
		switch(indentifierType) {
		
		case "XPATH":
			return BaseTest.driver.findElement(By.xpath(inentifierValue));
			
		case "CSS":
			return BaseTest.driver.findElement(By.cssSelector(inentifierValue));
		case "ID":
			return BaseTest.driver.findElement(By.id(inentifierValue));
		case "NAME":
			return BaseTest.driver.findElement(By.name(inentifierValue));
		case "LINKTEXT":
			return BaseTest.driver.findElement(By.linkText(inentifierValue));
		
		default:
			return null;
		}
	}
	
	public List<WebElement> getWeElements(String indentifierType, String inentifierValue) {
		switch(indentifierType) {
		
		case "XPATH":
			return BaseTest.driver.findElements(By.xpath(inentifierValue));
			
		case "CSS":
			return BaseTest.driver.findElements(By.cssSelector(inentifierValue));
		case "ID":
			return BaseTest.driver.findElements(By.id(inentifierValue));
		case "NAME":
			return BaseTest.driver.findElements(By.name(inentifierValue));
		case "LINKTEXT":
			return BaseTest.driver.findElements(By.linkText(inentifierValue));
		
		default:
			return null;
		}
	}
}
