package ca.dohado.framework.utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Utils {

    public static  List<String> getElementsStrings(List<WebElement> elements) {
        return elements
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static WebElement getElementByName(List<WebElement> elements, String name) {
        return elements.stream()
                .filter(el -> el.getText().equals(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

}
