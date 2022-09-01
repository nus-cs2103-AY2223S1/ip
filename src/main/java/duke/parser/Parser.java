package duke.parser;

/**
 * Parses the user's commands.
 */
public class Parser {

    /**
     * Parses the specified command by splitting the string around whitespaces.
     * 
     * @param command Command to parse.
     * @return String array containing the tokens of the command.
     */
    public static String[] parseCommand(String command) {
        // Split string around whitespaces
        return command.split("\\s");
    }
    
}
