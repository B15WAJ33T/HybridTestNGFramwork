package com.tutorialNinja.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class CaptureScreenShot {

    public static String takeScreenShot(WebDriver driver, String testName) {

        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenShotPath = System.getProperty("user.dir")+"\\ScreenShot\\+testName+.png";

        try {
            FileHandler.copy(screenShot, new File(screenShotPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenShotPath;
    }
}
