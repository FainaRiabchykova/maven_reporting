package com.ria.actions;

import io.qameta.allure.Step;

public class MessageActions extends BaseActions {

    @Step("Step: User creates Draft Letter {emailData.getSubject()}")
    public void createDraftLetter() {
        //Click on “compose” button
        gmailPage.waitVisibilityOfElement(TIME_TO_WAIT, gmailPage.getComposeButton());
        gmailPage.clickOnComposeButton();
        logger.info("Composed Button was successfully clicked");
        //Fill the next fields: to, cc, bcc, subject & message
        gmailPage.clickOnCCLink();
        gmailPage.clickOnBCCLink();
        gmailPage.fillTOField(emailData.getTo());
        gmailPage.fillCCField(emailData.getCc());
        gmailPage.fillBCCField(emailData.getBcc());
        gmailPage.fillSubjectField(emailData.getSubject());
        logger.info("Email data was successfully filled: toEmail: {},  ccEmail: {},  bccEmail: {},  subjectEmail: {},",
                emailData.getTo(), emailData.getCc(), emailData.getBcc(), emailData.getSubject());

        //Click on “save & close” button
        gmailPage.clickOnCloseAndSave();
        logger.info("Save & Close Button was successfully clicked");
    }

    public void openDraftLetter() {
        //Go to the “draft” folder & open previously saved message
        gmailPage.waitInVisibilityOfElement(TIME_TO_WAIT, gmailPage.getCloseAndSave());
        gmailPage.clickOnDraftCategory();
        draftPage.waitURLContains(TIME_TO_WAIT, DRAFT_PAGE_URL_KEYWORD);
        logger.info("Draft Category was successfully opened");

        draftPage.clickOnDraftItem();
        logger.info("Draft Item was successfully opened");
        draftPage.waitElementToBeClickable(TIME_TO_WAIT, draftPage.getDraftLetterCClinks());

        draftPage.clickOnDraftLetterCClinks();
        logger.info("All draft letter links was successfully opened");

    }

    public void sendDraftLetter() {
        //Press the “send” button
        draftPage.clickOnSendButton();
    }
}
