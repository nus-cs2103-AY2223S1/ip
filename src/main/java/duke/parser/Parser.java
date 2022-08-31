package duke.parser;

public class Parser {

    public static String[] parseCommand(String command) {
        // Split string around whitespaces
        return command.split("\\s");
    }
    
}
