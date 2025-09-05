package com.planit.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extent = createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private static ExtentReports createInstance() {
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("target/ExtentReport.html");
        reporter.config().setDocumentTitle("Planit Automation Test Report");
        reporter.config().setReportName("UI Automation Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }

    @Override public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }
    @Override public void onTestSuccess(ITestResult result) { test.get().pass("Test passed"); }
    @Override public void onTestSkipped(ITestResult result) { test.get().skip("Test skipped"); }

    @Override public void onTestFailure(ITestResult result) {
        WebDriver driver = com.planit.utils.DriverFactory.getDriver();
        String path = takeScreenshot(driver, result.getMethod().getMethodName());
        if (path != null) test.get().fail(result.getThrowable()).addScreenCaptureFromPath(path);
        else test.get().fail(result.getThrowable());
    }

    @Override public void onFinish(ITestContext context) { extent.flush(); }

    private String takeScreenshot(WebDriver driver, String method){
        if (driver == null) return null;
        String ts = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fp = "target/screenshots/" + method + "_" + ts + ".png";
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            Files.createDirectories(Paths.get("target/screenshots/"));
            Files.copy(src.toPath(), Paths.get(fp));
            return fp;
        }catch(IOException e){ e.printStackTrace(); return null; }
    }
}
