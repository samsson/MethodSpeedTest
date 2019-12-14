package speedtest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import functions.MathFunctions;

public class Test{
    public static final String commandLine = SpeedTest.commandLine;
    public static final String commandLineArguments = SpeedTest.commandLineArguments;

    public static List<Integer> runtest(final String methodName1)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
            SecurityException {
        int a = 0;
        final List<Integer> run1 = new ArrayList<Integer>();
        long startTime;
        long runtime1 = 0;
    
        Method method = MethodLibrary.class.getDeclaredMethod(methodName1);

        do {
            for(int i = 0; i < 1000; ++i){

                startTime = System.nanoTime();
                method.invoke(null);
                runtime1 = runtime1 +  System.nanoTime() - startTime;
            }

            run1.add((int)runtime1);
            runtime1 = 0;
            a++;
        }
        while (a < SpeedTest.count);
        return run1;
    }
}