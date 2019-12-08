package speedtest;

public class LibraryFunctions {

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

}