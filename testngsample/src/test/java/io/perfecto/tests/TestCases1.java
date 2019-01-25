package io.perfecto.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import io.perfecto.testng.TestCaseInputs;
import io.perfecto.testng.TestCases;
import io.perfecto.testng.TestNGDataProvider;

// Test Class
public class TestCases1 extends TestCases{

	private static final long serialVersionUID = 1L;
	private TestCaseInputs inputs;
	
	// Test Data Factory
	@Factory(dataProviderClass=TestNGDataProvider.class,dataProvider=TestNGDataProvider.DATAPROVIDER)
	public TestCases1(TestCaseInputs inputs) {
		super(inputs);
		this.inputs = inputs;
	}
	
	// Test Setup
	@BeforeMethod(groups= {"Failed_Test","Passed_Test"})
	public void setup() {
		System.out.println("Test Setup");
	}
	
	// Test Case - 1
	@Test(groups= {"Failed_Test"},description="Check Perfecto title")
	public void testA() {
		
		// start step
		getReport().startStep("Goto website");
		
		WebDriver driver = (WebDriver)getDriver();
		driver.get("https://www.perfecto.io/");
		Assert.assertEquals(driver.getTitle(), "Not able to create new user");
	}

	// Test Case - 1	
	@Test(groups= {"Passed_Test"},description="Check Perfecto title")
	public void testB() {

		// start step
		getReport().startStep("Goto website");
		
		WebDriver driver = (WebDriver)getDriver();
		driver.get("https://www.perfecto.io/");
		Assert.assertEquals(driver.getTitle(), "Not able to create new user");
	}
	
	// Test tear down
	@AfterMethod(groups= {"Failed_Test","Passed_Test"})
	public void tearDown() {
		System.out.println("Test Tear Down");
	}
	
}
