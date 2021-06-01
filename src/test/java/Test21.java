
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Test21 {
    public enum Priority {
        Blocker, Critical, Major, Minor
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface TestMethodInfo {

        Priority priority() default  Priority.Major;

        String author() default  "Jim Bim";

        String lastModified() default  "01.01.2019";
    }

    @Test
    @TestMethodInfo(priority = Priority.Critical, author = "Test user", lastModified = "20.08.2019")
    public void annotation() {
        Assert.assertEquals(1, 1);
        System.out.println("Hi");

    }
}
