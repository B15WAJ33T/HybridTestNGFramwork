package com.tutorialNinja.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generateExtentReport() {
        ExtentReports extent = new ExtentReports();
        File extentFile = new File(System.getProperty("user.dir")+"\\target\\test-reports\\Extent-Reports\\extentReports.html");

        ExtentSparkReporter spark = new ExtentSparkReporter(extentFile);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("TutorialNinjas Test Result");
        spark.config().setDocumentTitle("TN Automation Test Reports");
        spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        Properties prop = new Properties();
        File configFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\config.properties");
        try {
            FileInputStream fis = new FileInputStream(configFile);
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        extent.setSystemInfo("URL", prop.getProperty("url"));
        extent.setSystemInfo("Browser", prop.getProperty("browser"));
        extent.setSystemInfo("Email", prop.getProperty("validEmail"));
        extent.setSystemInfo("Password", prop.getProperty("validPassword"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }

}