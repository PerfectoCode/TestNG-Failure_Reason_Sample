package io.perfecto.testng;

import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;
import org.testng.xml.XmlTest;

public class TestNGDataProvider {
	
	public final static String DATAPROVIDER="DataProvider";
	
	@DataProvider(name=DATAPROVIDER,parallel=true)
	public static Object[][] getTestData(ITestContext test, ITestNGMethod methos){
		
		XmlTest xmlTest = test.getCurrentXmlTest();
		List<String> tags = xmlTest.getIncludedGroups();
		String groupName = "";

		if(tags.size()>0) {
			groupName = tags.get(0);
		}
		
		Map<String,String> parameters = xmlTest.getAllParameters();
		
		return GenerateTestData.getData(groupName,parameters);
	}
}
