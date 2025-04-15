package ca.dohado.framework.config;

import com.aventstack.extentreports.ExtentReports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CleanUpBean implements DisposableBean {

    @Autowired
    private ExtentReports extentReports;
    private final Logger logger = LoggerFactory.getLogger(CleanUpBean.class);

    @Override
    public void destroy() throws Exception {
        logger.info("Starting cleaning up");
        extentReports.flush();
        logger.info("Clean up is finished");
    }
}
