package ca.dohado.tests;

import ca.dohado.config.CustomTestExecutionListener;
import ca.dohado.pages.LoginPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestExecutionListeners(
        value = { CustomTestExecutionListener.class },
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
    @Autowired
    protected LoginPage loginPage;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected SoftAssertions softly;

    @BeforeEach
    public void globalBeforeEach(TestInfo testInfo) {
        softly = new SoftAssertions();
        logger.info("Executing test {}", testInfo.getDisplayName());
        loginPage.openLoginPage();
    }

    @AfterEach
    public void globalAfterEach(TestInfo testInfo) {
        logger.info("Finished test {}", testInfo.getDisplayName());
    }
}
