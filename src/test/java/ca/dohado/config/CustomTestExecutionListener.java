package ca.dohado.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opentest4j.TestAbortedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import java.io.File;
import java.io.IOException;

@Order
public class CustomTestExecutionListener implements TestExecutionListener {
    private ExtentTest classTest;
    private final ThreadLocal<String> testCaseNameThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>(); //Thread safe
    private String reportPath;
    private final Logger logger = LoggerFactory.getLogger(CustomTestExecutionListener.class);

    @Override
    public void prepareTestInstance(TestContext testContext) {
        reportPath = testContext.getApplicationContext().getEnvironment().getProperty("testreport.extentreport.path");
    }

    @Override
    public void beforeTestClass(TestContext testContext) {
        ExtentReports extentReports = testContext.getApplicationContext().getBean(ExtentReports.class);
        classTest = extentReports.createTest(testContext.getTestClass().getSimpleName());
        logger.debug("Extent report is {}", extentReports);
    }

    @Override
    public void beforeTestMethod(TestContext testContext) {
        testCaseNameThreadLocal.set(testContext.getTestMethod().getName());
        ExtentTest test = classTest.createNode(testCaseNameThreadLocal.get());
        extentTestThreadLocal.set(test);
    }

    @Override
    public void afterTestMethod(TestContext testContext) {
        Throwable testException = testContext.getTestException();
        if (testException != null) {
            logger.debug("Test exception is ", testException);
            if (testException.getClass().equals(TestAbortedException.class)) {
                extentTestThreadLocal.get().log(Status.SKIP, "Skipped due to " + testException);
            } else {
                extentTestThreadLocal.get().fail(testContext.getTestException());
                try {
                    extentTestThreadLocal.get().addScreenCaptureFromPath(getScreenshot(testContext));
                } catch (Exception e) {
                    logger.warn("Unable to get a screenshot, exception was thrown: {}", e.getMessage());
                }
            }
        } else {
            extentTestThreadLocal.get().log(Status.PASS, "Test Passed");
        }

        testContext.getApplicationContext().getBean(WebDriver.class).quit();
    }

    //TODO: move to a separate service class
    private String getScreenshot(TestContext testContext) throws IOException {
        WebDriver driver = testContext.getApplicationContext().getBean(WebDriver.class);
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File output = new File(reportPath + testCaseNameThreadLocal.get() + ".png");
        FileUtils.copyFile(source, output);
        return output.getName();
    }

}
