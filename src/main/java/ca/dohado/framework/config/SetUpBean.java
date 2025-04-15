package ca.dohado.framework.config;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SetUpBean implements InitializingBean {

    @Value("${download.path}")
    private String downloadPath;
    @Value("${testreport.extentreport.path}")
    private String extentReportPath;
    private final Logger logger = LoggerFactory.getLogger(SetUpBean.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Bean postprocessing");
        processDir(downloadPath);
        processDir(extentReportPath);
    }

    private void processDir(String targetPath) throws Exception {
        File targetDirectory = new File(targetPath);
        try {
            if (targetDirectory.exists()
                    && targetDirectory.list() != null
                    && targetDirectory.list().length > 0)
            {
                for (String fileName : targetDirectory.list()) {
                    String filePath = targetPath + fileName;
                    File file = new File(filePath);
                    logger.debug("Processing file {}", filePath);
                    if (file.isFile()) {
                        logger.debug("Deleting file {}", filePath);
                        FileUtils.delete(file);
                    } else if (file.isDirectory()) {
                        logger.debug("Deleting directory {}", filePath);
                        FileUtils.deleteDirectory(file);
                    }
                }
            } else if (!targetDirectory.exists()) {
                if (!targetDirectory.mkdirs()) {
                    logger.info("Unable to create a directory {}", targetDirectory);
                }
            }
        } catch (Exception e) {
            logger.info("Exception occurred during processing of directories/files needed for test reports", e);
            throw e;
        }
    }

}
