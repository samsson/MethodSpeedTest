package speedtest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import functions.MathFunctions;

public class SpeedTest {

    public static int count = 1000; //increase iterations
    public static String commandLine = "\"C:\\Windows\\system32\\cmd.exe\" /c calc.exe"; // whole commandline
    public static String commandLineArguments = "/c calc.exe"; // arguments to verify
    public static String regex = "calc\\.exe";
    public static List<Integer> run1 = new ArrayList<Integer>();
    public static List<Integer> run2 = new ArrayList<Integer>();
    public static List<Integer> run1sorted = new ArrayList<Integer>();
    public static List<Integer> run2sorted = new ArrayList<Integer>();
    public static double run1MedianDouble;
    public static double run2MedianDouble;
    public static int MAX_SCORE;
    public static int MAX_SCORE2;

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        
        run1 = Test.runtest("equalsfunction");
        run2 = Test.runtest("matchesfunction");
        run1sorted = MathFunctions.getSortedList(run1);
        run2sorted = MathFunctions.getSortedList(run2);
        run1MedianDouble = MathFunctions.median(run1);
        run2MedianDouble = MathFunctions.median(run2);
        MAX_SCORE = run1sorted.get(0);
        MAX_SCORE2 = run2sorted.get(0);

        histogram.DrawGraph.createAndShowGui();
    }
}