package io.perfecto.tests;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.perfecto.reporting.Reports;
import io.perfecto.utils.Logger;
import io.perfecto.utils.Logger.STATUS;

public class MainTest {

	public static void main(String[] args) {
		
		Logger.log(STATUS.INFO, "================ Test started ===================");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("platformName", "Windows");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("location", "US East");
		capabilities.setCapability("securityToken", "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJzbFV4OFFBdjdVellIajd4YWstR0tTbE43UjFNSllDbC1TRVJiTlU1RFlFIn0.eyJqdGkiOiJkYzhjZTZjYS04ZWM3LTQ4MWUtOTZhZS0wZTYzOWU0NGM4ZmUiLCJleHAiOjAsIm5iZiI6MCwiaWF0IjoxNTQ3NzIwNzE4LCJpc3MiOiJodHRwczovL2F1dGgucGVyZmVjdG9tb2JpbGUuY29tL2F1dGgvcmVhbG1zL2RlbW8tcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJzdWIiOiIwYmVkN2M2Ni1mZTk0LTRiNzItOWQxMC1iYjg5OTRmZDhhOWYiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6IjRhMDlmOTQ4LTQ0NTctNDdiOC04ODc5LTdkMzAwYzdlMDZjNSIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjEwYzY1MWI0LTY3NWQtNDA3MC1hMThkLThiZTFmYjNmNTFlNyIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fX0.pUroxxPORqdGtmvLciD3EtAi6lQUd5ljwLFCKNz8tJF5DvYpw0fV7W0gYSGVrZyNl4QO6bQUpKD4eH_V5BryeEULt-BF2oMJiVFg_nbRV1zmN_t14QZNKmsjjRbWwZanXW4NMF8tts6dl_zz_sNWW2HOHuPQSStyuCaMRMT2ndo98Ner-sXLNN1l83kFABp-ulrmfpLmjimnISyzfyVE60GnBn7XhqKvEAljJ7lhbD2H1PwhTZh5tZl4Ivm3fnCNFZxOTViubW_QcCfXR8a-vUCSh7PMaU52rL-5HXJDj73pERtFBiyu0rRkB4ZIJgKJhPAh7uUg_rwmijhwmG_pIA");
		
		RemoteWebDriver driver = null;
		try {
			// Initialize Driver
			driver = new RemoteWebDriver(new URL("https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub/fast"), capabilities);
			
			// Initialize Digital Zoom driver
			Reports report = new Reports(driver);

			// Start Digital Zoom Test
			report.startTest("Sample Test");
			
			// Start Digital Zoom Test step
			report.startStep("Goto Website");
			
			driver.get("https://www.perfecto.io/");
			
			// End Digital Zoom Test step
			report.endStep();
			
			// End Digital Zoom Test
			report.endTest(false, new Exception("Not able to create new user - A"));
			
		}catch(Exception e) {
			Logger.log(STATUS.INFO, e.getMessage());
		}finally {
			if(driver!=null) {
				driver.quit();
			}
			Logger.log(STATUS.INFO, "================ Test Completed ===================");
		}
		
	}

}
