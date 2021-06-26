package com.ria.actions;

import com.ria.services.User;
import io.qameta.allure.Step;

public class SignInActions extends BaseActions{

    @Step("Step: User is signing in")
    public void signIn(User user) {
        //checkLoginToAccount
        logger.info("User starts signing in with email: {} and password: {}", user.getEmail(), user.getPassword() );
        signInPage.waitVisibilityOfElement(TIME_TO_WAIT, signInPage.getEmailInputField());
        signInPage.emailSubmit(user.getEmail());
        signInPage.implicitWait(TIME_TO_WAIT);
        signInPage.passwordSubmit(user.getPassword());
        signInPage.implicitWait(TIME_TO_WAIT);
        signInPage.waitURLContains(TIME_TO_WAIT, MAIL_URL_KEYWORD);
        logger.info("User is signed in successfully");
    }

}
