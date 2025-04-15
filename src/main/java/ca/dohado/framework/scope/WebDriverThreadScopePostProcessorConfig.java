package ca.dohado.framework.scope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverThreadScopePostProcessorConfig {

    @Bean
    public static BeanFactoryPostProcessor webDriverThreadScopePostProcessor() {
        return beanFactory -> {
            beanFactory.registerScope("webdriverthreadscope", new WebDriverThreadScope());
        };
    }
}
