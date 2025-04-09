package ca.dohado.framework.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtentReporter {

    @Bean
    public ExtentReports getReporter(@Value("${testreport.extentreport.path}") String reportPath,
                                     @Value("${spring.profiles.active}") String env,
                                     @Value("${browser}") String browser) {
        String path = reportPath + "FrontendTestReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Westland frontend testing report: ENV -> " + env.toUpperCase() + ", BROWSER -> " + browser.toUpperCase());
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }

}
