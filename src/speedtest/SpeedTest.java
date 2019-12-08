package speedtest;

import java.util.ArrayList;
import java.util.List;

public class SpeedTest {
    static String commandLine = "\"C:\\Windows\\system32\\cmd.exe\" /c calc.exe";
    static String commandLineArguments = "/c calc.exe";

    public static void main(final String[] args) {

        int a = 0;
        boolean equalsFunction = false;
        boolean matchesFunction = false;
        List<Long> run1 = new ArrayList<Long>();
        List<Long> run2 = new ArrayList<Long>();
        long startTime;
        long runtime1 = 0;
        long runtime2 = 0;

        System.out.println("Preparing tests ");
        equalsFunction = LibraryFunctions.equalsfunction(commandLine, commandLineArguments);
        matchesFunction = LibraryFunctions.matchesfunction(commandLine);
        System.out.println("Ok Running test: ");
        
        do {
            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                /* Your function call here */
                equalsFunction = LibraryFunctions.equalsfunction(commandLine, commandLineArguments);
                
                
                runtime1 = runtime1 +  System.nanoTime() - startTime;
            }

            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                /* Your second function call here */
                matchesFunction = LibraryFunctions.matchesfunction(commandLine);
                
                
                runtime2 = runtime2 + System.nanoTime() - startTime;
            }

            System.out.println("Equals & matches in milli: " + equalsFunction + " " + runtime1 + " " + matchesFunction + " " + runtime2 + " "); 

            run1.add(runtime1);
            run2.add(runtime2);
            runtime1 = 0;
            runtime2 = 0;
            /* 
            try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
            }
             */
            a++;
          }
        while (a < 500);
        
        int runMedian1 = (int)MathFunctions.median(run1);
        int runMedian2 = (int)MathFunctions.median(run2);
        
        System.out.println("Test complete.");
        //System.out.println("Lowest numbers in millisec out of: " + a + " rounds, Equals: " + lowestEqual + " Matches: " + lowestMatches);
        System.out.println("Median result Equals: " + MathFunctions.median(run1)/100000 + "ms Matches: " + MathFunctions.median(run2)/100000+ "ms");
        
        if (runMedian1 < runMedian2)
        {
            System.out.println("Equals is " + MathFunctions.getPercentage(runMedian2, runMedian1, a) + " percent faster.");
            /* 
            final double q = (((double)averageSum(matchesSum, a) - averageSum(equalSum, a)) / averageSum(matchesSum, a))*100;
            final float percentage2 = (float)q;
            System.out.println("Equals is " + percentage2 + " percent faster.");
             */
        } 
        else
        {
            System.out.println("Matches is " + MathFunctions.getPercentage(runMedian1, runMedian2, a) + " percent faster.");
        }

    }
}

