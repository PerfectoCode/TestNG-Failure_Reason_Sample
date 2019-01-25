package io.perfecto.testng;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import io.perfecto.utils.Logger;
import io.perfecto.utils.Logger.STATUS;

public class GroupLevelDriverInitialization implements ITestListener, IInvokedMethodListener {

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		TestCases testCase = (TestCases) result.getInstance();

//		testCase.getReport().endTest(true);

		Logger.log(STATUS.INFO, "================================ [PASS] Test Execution Completed - "
				+ testCase.getTestName() + " ================================");
	}

	@Override
	public void onTestFailure(ITestResult testResult) {

		TestCases testCase = (TestCases) testResult.getInstance();

//		if (testCase.getReport() != null) {
//			 testCase.getReport().endTest(false, testResult.getThrowable());
//		}

		Logger.log(STATUS.INFO, "================================ [FAIL] Test Execution Failed - "
				+ testCase.getTestName() + " ================================");
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		TestCases testCase = (TestCases) testResult.getInstance();

//		if (testCase.getReport() != null) {
//			 testCase.getReport().endTest(false, testResult.getThrowable());
//		}

		Logger.log(STATUS.INFO, "================================ [FAIL] Test Execution Failed - "
				+ testCase.getTestName() + " ================================");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {

	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {

		ITestNGMethod meth = method.getTestMethod();
		
		if (method.isConfigurationMethod()) {

//			

			

//			TestCases testCase = (TestCases) testResult.getInstance();
//
//			TestCaseInputs inputs = testCase.getTestCaseInputs();
//
//			Map<String, String> xmlParameters = inputs.getParameters();
//
//			Device deviceInformation = new Device(xmlParameters);
//			
//			testCase.setDeviceInformation(deviceInformation);
//
//			SearchContext webDriver = null;
//			
//			try {
//
//				testCase.setDeviceAllocated(DeviceUtils.isDeviceAvailable(deviceInformation));
//				DesiredCapabilities desiredCapabilities = DesiredCapability.getDesiredCapabilities(deviceInformation);
//				PerfectoCloudInformation cloudInformation = PerfectoCloudInformation.getCloudInformation();
//
//				try {
//					if (deviceInformation.isMobileDevice()) {
//						if (deviceInformation.isAndroid()) {
//							webDriver = new AndroidDriver<>(new URL(cloudInformation.getHubUrl()), desiredCapabilities);
//						} else {
//							webDriver = new IOSDriver<>(new URL(cloudInformation.getHubUrl()), desiredCapabilities);
//						}
//
//					} else {
//						webDriver = new RemoteWebDriver(new URL(cloudInformation.getFastHubUrl()), desiredCapabilities);
//					}
//
//				} catch (MalformedURLException e) {
//					Logger.log(STATUS.FAIL, e.getLocalizedMessage());
//				} catch (WebDriverException e) {
//					Logger.log(STATUS.FAIL, e.getLocalizedMessage());
//				}
//
//			} catch (TimeoutException e) {
//				Logger.log(STATUS.FAIL, e.getLocalizedMessage());
//			}finally {
//				Reports report = new Reports(webDriver);
//				testCase.setDriver(webDriver);
//				testCase.setReportiumClient(report);
//			}

		} else {
			if (method.isTestMethod()) {

				TestCases testCase = (TestCases) testResult.getInstance();
				testCase.setTestName(method.getTestMethod().getDescription());

				Logger.log(STATUS.INFO, "================================ Test Execution Started - "
						+ method.getTestMethod().getDescription() + " ================================");
//				Reports report = testCase.getReport();
//				
//				if(testCase.getDriver()==null) {
//					throw new WebDriverException("Driver not initialized");
//				}else {
//					report.startTest(testCase.getTestName());
//				}
			}else {
				if(meth.isBeforeGroupsConfiguration()) {
					System.out.println("--------------");
				}
			}
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod meth = method.getTestMethod();
		if (method.isConfigurationMethod()) {
			TestCases testCase = (TestCases) testResult.getInstance();
			testCase.setTestName(method.getTestMethod().getDescription());
			if (testCase.getDriver() != null) {
				((WebDriver) testCase.getDriver()).quit();
			}
		}else {
			if(meth.isAfterGroupsConfiguration()) {
				
			}
		}
	}

//	@Override
//	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
//		ITestNGMethod meth = method.getTestMethod();
//		if (method.isConfigurationMethod()) {
//
//		} else {
//			if (meth.isBeforeGroupsConfiguration()) {
//				System.out.println("-----------");
//			}else {
//				if(method.isTestMethod()) {
//					TestCases testCase = (TestCases) testResult.getInstance();
//					testCase.setTestName(method.getTestMethod().getDescription());
//
//					Logger.log(STATUS.INFO, "================================ Test Execution Started - "
//							+ method.getTestMethod().getDescription() + " ================================");
//
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
//		// TODO Auto-generated method stub
//
//	}

}
