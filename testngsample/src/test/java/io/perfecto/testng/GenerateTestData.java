package io.perfecto.testng;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenerateTestData {

	public static Object[][] getData(String groupName, Map<String,String> parameters){
		
		List<TestCaseInputs> testCaseInputs = getInputs(groupName, parameters);
		
		Object[][] testData = new Object[testCaseInputs.size()][1];
		
		for(int index=0;index<testCaseInputs.size();++index) {
			testData[index][0] = testCaseInputs.get(index);
		}
		
		return testData;
	}
	
	private static List<TestCaseInputs> getInputs(String groupName, Map<String,String> parameters){
		
		List<TestCaseInputs> testCaseInputs = new ArrayList<>();
		testCaseInputs.add(new TestCaseInputs(groupName, parameters));
		return testCaseInputs;
	}
	
}
