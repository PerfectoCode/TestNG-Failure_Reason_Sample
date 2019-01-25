package io.perfecto.testng;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.perfecto.devices.DesiredCapability;
import io.perfecto.devices.Device;
import io.perfecto.reporting.Reports;
import io.perfecto.utils.DeviceUtils;
import io.perfecto.utils.Logger;
import io.perfecto.utils.Logger.STATUS;
import io.perfecto.utils.PerfectoCloudInformation;

public class TestLevelDriverInitialization implements ITestListener, IInvokedMethodListener2 {

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		TestCases testCase = (TestCases) result.getInstance();

		testCase.getReport().endTest(true);

		Logger.log(STATUS.INFO, "================================ [PASS] Test Execution Completed - "
				+ testCase.getTestName() + " ================================");
	}

	@Override
	public void onTestFailure(ITestResult testResult) {

		TestCases testCase = (TestCases) testResult.getInstance();

		if (testCase.getReport() != null) {
			testCase.getReport().endTest(false, testResult.getThrowable());
		}

		Logger.log(STATUS.INFO, "================================ [FAIL] Test Execution Failed - "
				+ testCase.getTestName() + " ================================");
	}

	@Override
	public void onTestSkipped(ITestResult testResult) {
		TestCases testCase = (TestCases) testResult.getInstance();

		if (testCase.getReport() != null) {
			testCase.getReport().endTest(false, testResult.getThrowable());
		}

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
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		if (method.isConfigurationMethod()) {

			if (method.getTestMethod().isBeforeMethodConfiguration()) {

				TestCases testCase = (TestCases) testResult.getInstance();

				TestCaseInputs inputs = testCase.getTestCaseInputs();

				Map<String, String> xmlParameters = inputs.getParameters();

				Device deviceInformation = new Device(xmlParameters);

				testCase.setDeviceInformation(deviceInformation);

				SearchContext webDriver = null;

				try {

					testCase.setDeviceAllocated(DeviceUtils.isDeviceAvailable(deviceInformation));
					
					DesiredCapabilities desiredCapabilities = DesiredCapability
							.getDesiredCapabilities(deviceInformation);
					
					PerfectoCloudInformation cloudInformation = PerfectoCloudInformation.getCloudInformation();

					try {
						if (deviceInformation.isMobileDevice()) {
							
							if (deviceInformation.isAndroid()) {
								webDriver = new AndroidDriver<>(new URL(cloudInformation.getHubUrl()),
										desiredCapabilities);
							} else {
								webDriver = new IOSDriver<>(new URL(cloudInformation.getHubUrl()), desiredCapabilities);
							}

						} else {
							webDriver = new RemoteWebDriver(new URL(cloudInformation.getFastHubUrl()),
									desiredCapabilities);
						}

					} catch (MalformedURLException e) {
						Logger.log(STATUS.FAIL, e.getLocalizedMessage());
					} catch (WebDriverException e) {
						Logger.log(STATUS.FAIL, e.getLocalizedMessage());
					}

				} catch (TimeoutException e) {
					Logger.log(STATUS.FAIL, e.getLocalizedMessage());
				} finally {
					Reports report = new Reports(webDriver);
					testCase.setDriver(webDriver);
					testCase.setReportiumClient(report);
				}
			}

		} else {
			if (method.isTestMethod()) {

				TestCases testCase = (TestCases) testResult.getInstance();
				testCase.setTestName(method.getTestMethod().getDescription());

				Logger.log(STATUS.INFO, "================================ Test Execution Started - "
						+ method.getTestMethod().getDescription() + " ================================");
				Reports report = testCase.getReport();

				if (testCase.getDriver() == null) {
					throw new WebDriverException("Driver not initialized");
				} else {
					report.startTest(testCase.getTestName());
				}
			}
		}

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
		if (method.isConfigurationMethod() && method.getTestMethod().isAfterMethodConfiguration()) {
			TestCases testCase = (TestCases) testResult.getInstance();
			testCase.setTestName(method.getTestMethod().getDescription());
			if (testCase.getDriver() != null) {
				((WebDriver) testCase.getDriver()).quit();
			}
		}

	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
	}

}
