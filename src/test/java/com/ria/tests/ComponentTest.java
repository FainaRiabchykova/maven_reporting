package com.ria.tests;

import com.ria.actions.Assertions;
import com.ria.actions.MessageActions;
import com.ria.actions.SignInActions;
import com.ria.services.DataProvider;
import com.ria.services.User;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ria.services.TestListener.class)

public class ComponentTest extends BaseTest{

    @Description("Description: Test for checking Draft Letter is saved in Gmail")
    @Test(dataProvider = "riaTest", dataProviderClass = DataProvider.class)
    public void checkSaveDraftsInGmail(User user) throws InterruptedException {

        SignInActions signInActions = new SignInActions();
        MessageActions messageActions = new MessageActions();
        Assertions messageAsserts = new Assertions();

        signInActions.signIn(user);
        messageActions.createDraftLetter();
        messageActions.openDraftLetter();
        messageAsserts.assertDraftLetterFieldsAreCorrect();
        messageActions.sendDraftLetter();
    }
    @Description("Description: Test is failed")
    @Test
    public void testFail() {
        Assert.assertTrue(false);
    }
}
