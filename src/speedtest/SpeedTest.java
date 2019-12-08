package speedtest;

public class SpeedTest {
    static String commandLine = "\"C:\\Windows\\system32\\cmd.exe\" /c calc.exe";
    static String commandLineArguments = "/c calc.exe";

    public static int averageSum(final long value, final int count)
    {
        // Calculate average result
        return (int)value / count;

    }

    // Parse commandline arguments from CommandLine fields
    public static String parseCommandArguments(final String parse_command_line)
    {
        try
        {
            //if (parse_command_line.startsWith("\""))
            if (parse_command_line.matches("^\""))
            {
                // Take everything after quotes and a trailing space
                final String[] command_line_parsed = parse_command_line.split("^\".*?\" ", 2);
                return command_line_parsed[1];
            }
            else
            {
                // No starting quote found so take everything after first space
                final String[] command_line_parsed = parse_command_line.split(" ", 2);
                return command_line_parsed[1];
            }
        }
        catch(final Exception ex)
        {
            // Exception, most likely index due missing arguments, return empty arguments
            return "";
        }
    }
    public static boolean equalsfunction(final String commandline, final String commandLineArguments)
    {
        final String commandLineNew = parseCommandArguments(commandline);
        if (commandLineNew.equals(commandLineArguments))
        {
                return true;
        }
        return false;
    }
    public static boolean matchesfunction(final String commandline)
    {
        // .*calc\\.exe
        if (commandline.matches(".*calc\\.exe"))
        {
            return true;
        }
        return false;
    }

    public static void main(final String[] args) {
        int a = 0;
        boolean equalsFunction = false;
        boolean matchesFunction = false;
        long equalstime = 0;
        long matchestime = 0;
        long startTime;
        long equalSum = 0;
        long matchesSum = 0;

        System.out.println("Preparing tests ");
        equalsFunction = equalsfunction(commandLine, commandLineArguments);
        matchesFunction = matchesfunction(commandLine);
        System.out.println("Ok Running test: ");
        
        do {
            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                /* Your function call here */
                equalsFunction = equalsfunction(commandLine, commandLineArguments);
                equalstime = equalstime + System.nanoTime() - startTime;
            }

            for(int i = 0; i < 1000; ++i)
            {
                startTime = System.nanoTime();
                /* Your second function call here */
                matchesFunction = matchesfunction(commandLine);
                matchestime = matchestime + System.nanoTime() - startTime;
            }

            System.out.println("Equals & matches in milli: " + equalsFunction + " " + equalstime + " " + matchesFunction + " " + matchestime + " "); 
            // Calculate average result
            equalSum = equalSum + equalstime;
            matchesSum = matchesSum + matchestime;

            equalstime = 0;
            matchestime = 0;
            try {
				Thread.sleep(100);
			} catch (final InterruptedException e) {
				e.printStackTrace();
            }
            a++;
          }
        while (a < 100);
        System.out.println("Test complete.");
        //System.out.println("Lowest numbers in millisec out of: " + a + " rounds, Equals: " + lowestEqual + " Matches: " + lowestMatches);
        System.out.println("Average result Equals: " + averageSum(equalSum, a)/100000 + "ms Matches: " + averageSum(matchesSum, a)/100000+ "ms");
        if (averageSum(equalSum, a) < averageSum(matchesSum, a))
        {
            final double q = (((double)averageSum(matchesSum, a) - averageSum(equalSum, a)) / averageSum(matchesSum, a))*100;
            final float percentage2 = (float)q;
            System.out.println("Equals is " + percentage2 + " percent faster.");
        } 
        else
        {
            final double q = (((double)averageSum(equalSum, a) - averageSum(matchesSum, a)) / averageSum(equalSum, a))*100;
            final float percentage2 = (float)q;
            System.out.println("Matches is " + percentage2 + " percent faster.");
        }

    }
}

