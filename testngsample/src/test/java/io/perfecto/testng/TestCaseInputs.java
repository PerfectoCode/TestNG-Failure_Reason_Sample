package io.perfecto.testng;

import java.util.Map;

public class TestCaseInputs {

	private String groupName;
	private Map<String,String> parameters;
	
	public TestCaseInputs(String groupName,Map<String,String> parameters) {
		this.groupName = groupName;
		this.parameters = parameters;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}
	
}