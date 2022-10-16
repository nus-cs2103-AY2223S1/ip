package seedu.duke;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Converts a string of input from user to recognised Commands.
 */
public class Parser {
    /** possible commands in strings */
    private final ArrayList<String> stringOfCommands;
    /**
     * Constructor for Parser.
     */
    public Parser() {
        this.stringOfCommands = new ArrayList<>(Arrays.asList("BYE",
        "DEADLINE", "DELETE", "EVENT", "FIND", "LIST", "MARK", "TAG", "TODO", "UNMARK"));
    }

    /**
     * Returns a Command object.
     *
     * @param input string inputs by the user.
     * @return a Command equivalent of the input string.
     */
    public Command parse(String input) {
        String commandString = input.split(" ")[0].toUpperCase();
        Command command;
        if (stringOfCommands.contains(commandString)) {
            int index = stringOfCommands.indexOf(commandString);
            switch(index) {

            case 0:
                command = new ByeCommand();
                break;
            case 1:
                command = new DeadlineCommand();
                break;
            case 2:
                command = new DeleteCommand();
                break;
            case 3:
                command = new EventCommand();
                break;
            case 4:
                command = new FindCommand();
                break;
            case 5:
                command = new ListCommand();
                break;
            case 6:
                command = new MarkCommand();
                break;
            case 7:
                command = new TagCommand();
                break;
            case 8:
                command = new TodoCommand();
                break;
            case 9:
                command = new UnmarkCommand();
                break;

            default:
                command = new IllegalCommand();
            }

        } else {
            command = new IllegalCommand();
        }

        return command;
    }
}
