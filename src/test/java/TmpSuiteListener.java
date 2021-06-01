import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.lang.reflect.Method;

public class TmpSuiteListener  implements ISuiteListener {
    public static boolean start = false;
    public static boolean finish = false;

    @Override
    public void onFinish(ISuite suite) {

//        ISuiteListener.super.onFinish(suite);
        System.out.println("Finishing");

    }

    @Override
    public void onStart(ISuite suite) {

        Class testClass = Test21.class;
        try {
            Method method = testClass.getMethod("annotation", null);
            Test21.TestMethodInfo annotation = method.getAnnotation(Test21.TestMethodInfo.class);
            System.out.println(annotation.priority());
            System.out.println(annotation.author());
            System.out.println(annotation.lastModified());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("Starting");
    }
}
