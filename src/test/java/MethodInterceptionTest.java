import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface Selector {

    String xpath();
}

interface MainPage {

    @Selector(xpath = ".//*[@test-attr='input_search']")
    String textInputSearch();

    @Selector(xpath = ".//*[@test-attr='button_search']")
    String buttonSearch();
}

public class MethodInterceptionTest {

    @Test
    public void annotationValue() {
        MainPage mainPage = createPage(MainPage.class);
        Assertions.assertNotNull(mainPage);
        Assertions.assertEquals(mainPage.buttonSearch(), ".//*[@test-attr='button_search']");
        Assertions.assertEquals(mainPage.textInputSearch(), ".//*[@test-attr='input_search']");
    }

    private MainPage createPage(Class clazz) {
    return (MainPage) Proxy.newProxyInstance(
            ClassLoader.getSystemClassLoader(),
            new Class<?>[] { clazz },
            (proxy, method, args) -> {
                String name = method.getName();
                if (name.contains("textInputSearch") || (name.contains("buttonSearch"))) {
                    return  method.getAnnotation(Selector.class).xpath();
                }
                return null;
            });
    }
}
