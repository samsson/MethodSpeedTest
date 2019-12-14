package speedtest;

public class MethodLibrary {

    static String parseCommandArguments(final String parse_command_line)
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
    
    static boolean equalsfunction()
    {
        String commandLineNew = parseCommandArguments(Test.commandLine);
        if (commandLineNew.equals(Test.commandLineArguments))
        {
                return true;
        }
        return false;
    }
    static boolean equalsNofunction(String arguments, String equals)
    {
        if (arguments.equals(equals))
        {
                return true;
        }
        return false;
    }

    static boolean matchesfunction(String commandline, String regex)
    {
        if (commandline.matches(regex))
        {
            return true;
        }
        return false;
    }
    
    static boolean containsfunction(String commandline, String arguments)
    {
        if (commandline.contains(arguments))
        {
            return true;
        }
        return false;
    }

}