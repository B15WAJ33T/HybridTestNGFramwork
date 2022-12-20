package com.tutorialNinja.listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialNinja.utils.CaptureScreenShot;
import com.tutorialNinja.utils.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener{
    ExtentReports extentReport;
    ExtentTest extentTest;

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP,testName+" got skipped.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTest.log(Status.PASS, testName+" got successfully executed.");
    }

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String screenShotPath = CaptureScreenShot.takeScreenShot(driver, result.getTestName());

        extentTest.addScreenCaptureFromPath(screenShotPath);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName+" got failed.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO, testName+" has started executing");
    }
}
