package com.ria.actions;

import io.qameta.allure.Step;

import static org.testng.AssertJUnit.assertEquals;

public class Assertions extends BaseActions{

    @Step("Step: User checks Draft Letter Fields Are Correct")
    public void assertDraftLetterFieldsAreCorrect(){

        assertEquals(draftPage.getDraftLetterTOValue(), emailData.getTo());
        logger.info("Verification for 'TO' Email is done: actual value - {}; expected value - {}.", draftPage.getDraftLetterTOValue(), emailData.getTo());

        assertEquals(draftPage.getDraftLetterCCValue(), emailData.getCc());
        logger.info("Verification for 'CC' Email is done: actual value - {}; expected value - {}.", draftPage.getDraftLetterCCValue(), emailData.getCc());

        assertEquals(draftPage.getDraftLetterBCCValue(), emailData.getBcc());
        logger.info("Verification for 'BCC' Email is done: actual value - {}; expected value - {}.", draftPage.getDraftLetterBCCValue(), emailData.getBcc());

        assertEquals(draftPage.getDraftLetterSubject(), emailData.getSubject());
        logger.info("Verification for 'Subject' Email is done: actual value - {}; expected value - {}.", draftPage.getDraftLetterSubject(), emailData.getSubject());
        logger.info("=====================================================================================");
    }

}
