package ca.dohado.framework.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class FluentWaitFactory {

    public static <T> Wait<T> getFluentWait(T object, Duration timeOut, Duration polling) {
        return new FluentWait<T>(object)
                .withTimeout(timeOut)
                .pollingEvery(polling)
                .ignoring(NoSuchElementException.class);
    }

}
