package io.perfecto.testng;

import org.openqa.selenium.SearchContext;
import org.testng.xml.XmlTest;

import io.perfecto.devices.Device;
import io.perfecto.reporting.Reports;

public class TestCases extends XmlTest{

	private static final long serialVersionUID = 1L;
	
	private Reports reportium;
	private TestCaseInputs inputs;
	private boolean deviceAllocated = false;
	private SearchContext driver;
	private boolean closeStep = false;
	private Device deviceInformation;
	private String testName;
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Device getDeviceInformation() {
		return deviceInformation;
	}

	public void setDeviceInformation(Device deviceInformation) {
		this.deviceInformation = deviceInformation;
	}

	public TestCases(TestCaseInputs inputs) {
		this.inputs = inputs;
	}
	
	public TestCaseInputs getTestCaseInputs() {
		return this.inputs;
	}
	
	public void setReportiumClient(Reports reportium) {
		this.reportium = reportium;
	}
	
	public void StartStep(String stepDescription) {
		this.reportium.startStep(stepDescription);
	}
	
	public void EndStep() {
		this.reportium.endStep();
	}

	public boolean isDeviceAllocated() {
		return deviceAllocated;
	}

	public void setDeviceAllocated(boolean deviceAllocated) {
		this.deviceAllocated = deviceAllocated;
	}

	public SearchContext getDriver() {
		return driver;
	}

	public void setDriver(SearchContext driver) {
		this.driver = driver;
	}
	
	public Reports getReport() {
		return this.reportium;
	}
	
	public boolean closeStep() {
		return this.closeStep;
	}
	
	public void setCloseStep(boolean closeStep) {
		this.closeStep = closeStep;
	}
	
}
