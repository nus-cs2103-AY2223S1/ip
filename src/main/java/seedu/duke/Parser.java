package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Converts a string of input from user to recognised Commands.
 */
public class Parser {
    /* possible commands in strings */
    private final ArrayList<String> stringOfCommands;
    /**
     * Constructor for Parser.
     */
    public Parser() {
        this.stringOfCommands = new ArrayList<>(Arrays.asList("BYE",
        "DEADLINE", "DELETE", "EVENT", "LIST", "MARK", "TODO", "UNMARK"));
    }

    /**
     * Returns a Command object.
     * 
     * @param input string inputs by the user.
     * @return a Command equivalent of the input string.
     */
    public Command parse(String input) {
        String commandString = input.split(" ")[0].toUpperCase();
        Command command = Command.DEFAULT;
        if (stringOfCommands.contains(commandString)) {
            command = Command.valueOf(commandString);
        }
        return command;

    }
}
