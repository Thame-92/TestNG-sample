package org.testing;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.pom.LoginPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FireFoxBrowserTesting extends BaseClass {

	public static BaseClass b = new BaseClass();
	public static LoginPage l = new LoginPage();

	@Parameters({"browserType"})
	@Test(priority=1)
	public static void BrowserLaunch(@Optional("Edge")String browser) {

		try {
			b.getDriver(browser);

			System.out.println("FireFox Browser Launched Successfully");
		} catch (Exception e) {
			System.out.println("Browser Not launched" + e.getMessage());
		}
	}

	@Test(priority=2)
	public void UrlLaunch() {
		try {
			b.getUrl(b.excelRead(1, 3));

			System.out.println("Url Launched successfully");
		} catch (Exception e) {
			System.out.println("Url Not launched" + e.getMessage());
		}
	}

	@Parameters({"username","password"})
	@Test(priority=3)
	public void EnterCrendials(String user, String pass) {

		try {
			l = new LoginPage();
			WebElement username = l.getUsername();
			username.sendKeys(user);
			WebElement password = l.getPassword();
			password.sendKeys(pass);

			System.out.println("Credentials are Entered Successfully");
		} catch (Exception e) {
			System.out.println("Credentials are Not Entered" + e.getMessage());
		}
	}

	@Test(priority=4)
	public void LoginButtonClick() {

		try {
			l = new LoginPage();
			WebElement login = l.getLogin();
			login.click();

			System.out.println("Login Button Clicked Successfully");
		} catch (Exception e) {
			System.out.println("Login Button Not Clicked" + e.getMessage());
		}
		try {
			l = new LoginPage();
			WebElement location = l.getLocaion();
			b.selectByIndex(location, 6);

		} catch (Exception e) {

			System.out.println("Location selected Successfully" + e.getMessage());
		}
//		try {
//			l = new LoginPage();
//			WebElement location = l.getLocaion();
//			b.selectByIndex(location, 6);
//
//		} catch (Exception e) {
//
//			System.out.println("Location selected Successfully" + e.getMessage());
//		}
	}

}