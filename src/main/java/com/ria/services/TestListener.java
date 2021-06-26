package com.ria.services;



import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static java.lang.String.format;


    public class TestListener implements ITestListener {
        protected static Logger logger = LogManager.getLogger();

        private static String getTestMethodName(ITestResult iTestResult) {
            return iTestResult.getMethod().getConstructorOrMethod().getName();
        }

        //Text attachments for Allure
        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshotPNG(WebDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }

        //Text attachments for Allure
        @Attachment(value = "{0}", type = "text/plain")
        public static String saveTextLog(String message) {
            return message;
        }

        //HTML attachments for Allure
        @Attachment(value = "{0}", type = "text/html")
        public static String attachHtml(String html) {
            return html;
        }

        @Override
        public void onTestStart(ITestResult result){
            logger.info("{} started", result.getName());
            saveTextLog(getTestMethodName(result) + " started successfully - (onTestStart)");
        }
        @Override
        public void onTestSuccess(ITestResult result){
            logger.info("{} passed", result.getName());
            saveTextLog(getTestMethodName(result) + " finished successfully");
        }

        @Override
        public void onTestFailure(ITestResult result){
            logger.info("{} failed", result.getName());
            takeScreenshot();

            //Get driver from BaseTest and assign to local webdriver variable.
            //Object testClass = result.getInstance();
            WebDriver driver = DriverProvider.getDriver();

            //Allure ScreenShotRobot and SaveTestLog
            if (driver != null) {
                saveScreenshotPNG(driver);
            }

            //Save a log on allure.
            saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
        }

        @Override
        public void onTestSkipped(ITestResult iTestResult) {
            logger.info(getTestMethodName(iTestResult) + " test is skipped.");

        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

        }

        @Override
        public void onStart(ITestContext iTestContext) {


        }

        @Override
        public void onFinish(ITestContext iTestContext) {

        }

        public void takeScreenshot(){
            File screenshot = ((TakesScreenshot) DriverProvider.getDriver()).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File(format(".//target/screenshots/%s.png", LocalDate.now())));
            } catch (IOException e) {
                logger.error("Failed to save screenshot: {}", e.getMessage());
            }
        }
    }

