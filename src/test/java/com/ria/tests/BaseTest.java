package com.ria.tests;

import com.ria.pages.DraftPage;
import com.ria.pages.GmailPage;
import com.ria.pages.SignInPage;
import com.ria.services.DriverProvider;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
    private static final String SIGN_IN_URL = "https://mail.google.com";

    @Description("Description: Driver gets {SIGN_IN_URL}")
    @BeforeMethod
    public void testsSetUp() {
        DriverProvider.getDriver().get(SIGN_IN_URL);
    }

    @Description("Description: Driver closing")
    @AfterMethod
    public void tearDown() {
        DriverProvider.closeDriver();
    }

    public WebDriver getDriver() {
        return DriverProvider.getDriver();
    }

    public SignInPage getSignInPage() {
        return new SignInPage();
    }

    public GmailPage getGmailPage() {
        return new GmailPage();
    }

    public DraftPage getDraftPage() {
        return new DraftPage();
    }
}
