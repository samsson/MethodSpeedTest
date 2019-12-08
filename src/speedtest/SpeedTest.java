package speedtest;

import java.util.ArrayList;
import java.util.List;
import functions.MathFunctions;

public class SpeedTest {
    static String commandLine = "\"C:\\Windows\\system32\\cmd.exe\" /c calc.exe";
    static String commandLineArguments = "/c calc.exe";

    public static void main(String[] args) {

        int a = 0;
        boolean run1result = false;
        boolean run2result = false;
        List<Long> run1 = new ArrayList<Long>();
        List<Long> run2 = new ArrayList<Long>();
        long startTime;
        long runtime1 = 0;
        long runtime2 = 0;

        System.out.println("Preparing tests ");
        /* Your function calls here */
        run1result = MethodLibrary.equalsfunction(commandLine, commandLineArguments);
        run2result = MethodLibrary.matchesfunction(commandLine, ".*calc\\.exe");
        System.out.println("Ok Running test: ");
        
        do {
            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                /* Your function call here */
                run1result = MethodLibrary.equalsfunction(commandLine, commandLineArguments);
                runtime1 = runtime1 +  System.nanoTime() - startTime;
            }

            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                /* Your second function call here */
                run2result = MethodLibrary.matchesfunction(commandLine, ".*calc\\.exe");
                //run2result = MethodLibrary.containsfunction(commandLine, "calc.exe");
                runtime2 = runtime2 + System.nanoTime() - startTime;
            }

            //System.out.println("Equals & matches in milli: " + run1result + " " + runtime1 + " " + run2result + " " + runtime2 + " "); 

            run1.add(runtime1);
            run2.add(runtime2);
            runtime1 = 0;
            runtime2 = 0;
            a++;
          }
        while (a < 1000);
        
        int runMedian1 = (int)MathFunctions.median(run1);
        int runMedian2 = (int)MathFunctions.median(run2);
        
        System.out.println("Test complete.");
        System.out.println("Median result method1: " + run1result + " " + MathFunctions.median(run1)/100000 + "ms & method2: " + run2result + " " + MathFunctions.median(run2)/100000+ "ms");
        
        if (runMedian1 < runMedian2)
        {
            System.out.println("method1 is " + MathFunctions.getPercentage(runMedian2, runMedian1, a) + " percent faster.");
            /* 
            double q = (((double)averageSum(matchesSum, a) - averageSum(equalSum, a)) / averageSum(matchesSum, a))*100;
            float percentage2 = (float)q;
            System.out.println("Equals is " + percentage2 + " percent faster.");
             */
        } 
        else
        {
            System.out.println("method2 is " + MathFunctions.getPercentage(runMedian1, runMedian2, a) + " percent faster.");
        }

    }
}

