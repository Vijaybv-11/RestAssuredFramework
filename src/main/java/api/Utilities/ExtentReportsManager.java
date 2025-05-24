package api.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsManager implements ITestListener {

	String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH.mm.ss").format(new Date());
	public static ExtentReports extent;
	public static ExtentTest test;

	String reportName;

	public void onStart(ITestContext testcontext) {

		reportName = "Test-Report-" + timeStamp + ".html";

		// specify location of the Report

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Reports/" + reportName);

		sparkReporter.config().setDocumentTitle("Suite Exceution Report"); // Title of the project

		sparkReporter.config().setReportName("Regression Report Name"); // Name of the report

		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat(timeStamp);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "uat");
		extent.setSystemInfo("User", "Vijay");
		// test = extent.createTest(testcontext.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed Successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test has been Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test has been Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext context) {

		extent.flush();
	}

}