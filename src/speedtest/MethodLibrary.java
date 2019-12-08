package speedtest;

public class MethodLibrary {

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
    
    public static boolean equalsfunction(String commandline, final String commandLineArguments)
    {
        String commandLineNew = parseCommandArguments(commandline);
        if (commandLineNew.equals(commandLineArguments))
        {
                return true;
        }
        return false;
    }

    public static boolean matchesfunction(String commandline, String regex)
    {
        if (commandline.matches(regex))
        {
            return true;
        }
        return false;
    }
    
    public static boolean containsfunction(String commandline, String arguments)
    {
        if (commandline.matches(arguments))
        {
            return true;
        }
        return false;
    }

}