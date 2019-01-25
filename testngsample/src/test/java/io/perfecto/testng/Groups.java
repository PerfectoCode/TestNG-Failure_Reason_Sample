package io.perfecto.testng;

import org.openqa.selenium.SearchContext;

import io.perfecto.reporting.Reports;

public class Groups {
	private Reports reportium;
	private SearchContext driver;
	public Reports getReportium() {
		return reportium;
	}
	public void setReportium(Reports reportium) {
		this.reportium = reportium;
	}
	public SearchContext getDriver() {
		return driver;
	}
	public void setDriver(SearchContext driver) {
		this.driver = driver;
	}
	
	
	
}
