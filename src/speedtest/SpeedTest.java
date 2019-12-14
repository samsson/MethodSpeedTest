package speedtest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import functions.MathFunctions;

public class SpeedTest {

    public static int count = 1000; //increase iterations
    public static String commandLine = "\"C:\\Windows\\system32\\cmd.exe\" /c calc.exe"; // whole commandline
    public static String commandLineArguments = "/c calc.exe"; // arguments to verify
    public static List<Integer> run1 = new ArrayList<Integer>();
    public static List<Integer> run2 = new ArrayList<Integer>();
    public static List<Integer> run1sorted = new ArrayList<Integer>();
    public static List<Integer> run2sorted = new ArrayList<Integer>();
    public static double run1MedianDouble;
    public static double run2MedianDouble;

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        
        run1 = Test.runtest("equalsfunction");
        run2 = Test.runtest("equalsfunction");
        run1sorted = MathFunctions.getSortedList(run1);
        run2sorted = MathFunctions.getSortedList(run2);
        run1MedianDouble = MathFunctions.median(run1);
        run2MedianDouble = MathFunctions.median(run2);
        int MAX_SCORE = run1sorted.get(0);
        int MAX_SCORE2 = run2sorted.get(0);

        if (run1MedianDouble < run2MedianDouble)
        {
            System.out.println("method1 is " + MathFunctions.getPercentage((int)run2MedianDouble, (int)run1MedianDouble, count) + " percent faster.");
        } 
        else
        {
            System.out.println("method2 is " + MathFunctions.getPercentage((int)run1MedianDouble, (int)run2MedianDouble, count) + " percent faster.");
        }
        System.out.println();


        /* 
        do {
            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                //run1result = MethodLibrary.equalsfunction(commandLine, commandLineArguments);
                run1result = MethodLibrary.equalsNofunction(commandLineArguments, "/c calc.exe");
                //run1result = MethodLibrary.containsfunction(commandLine, "calc.exe");
                runtime1 = runtime1 +  System.nanoTime() - startTime;
            }

            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                //run2result = MethodLibrary.matchesfunction(commandLine, ".*calc\\.exe");
                run2result = MethodLibrary.equalsfunction(commandLine, commandLineArguments);
                //run2result = MethodLibrary.containsfunction(commandLine, "calc.exe");
                //run1result = MethodLibrary.containsfunction(commandLine, "calc.exe");
                runtime2 = runtime2 + System.nanoTime() - startTime;
            }

            run1.add((int)runtime1);
            run2.add((int)runtime2);
            runtime1 = 0;
            runtime2 = 0;
            a++;
          }
        while (a < 1000);
        
        double runMedianDouble = MathFunctions.median(run1);
        double runMedianDouble2 = MathFunctions.median(run2);
        
        System.out.println("Test complete.");
        System.out.println("Median result method1: " + run1result + " " + MathFunctions.median(run1)/100000 + "ms & method2: " + run2result + " " + MathFunctions.median(run2)/100000+ "ms");
        
        if (runMedianDouble < runMedianDouble2)
        {
            System.out.println("method1 is " + MathFunctions.getPercentage((int)runMedianDouble2, (int)runMedianDouble, a) + " percent faster.");
        } 
        else
        {
            System.out.println("method2 is " + MathFunctions.getPercentage((int)runMedianDouble, (int)runMedianDouble2, a) + " percent faster.");
        }
         */
    }
}