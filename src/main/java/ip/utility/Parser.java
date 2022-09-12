package ip.utility;

import java.util.Scanner;

import ip.command.*;
import ip.exception.InvalidCommand;

/**
 * Parses input from the user.
 */
public class Parser {
    /** String that the user has entered */
    private Scanner inputLine;

    /**
     * Stores user input string as a Scanner object.
     *
     * @param input String to be stored.
     */
    public void loadUserInput(String input) {
        inputLine = new Scanner(input);
    }

    /**
     * Parses user input for the command.
     * If no input is detected, it returns a ByeCommand object.
     *
     * @return An instance of the command entered by the user.
     * @throws InvalidCommand If the command entered is not recognised.
     */
    public DukeCommand getCommand() throws InvalidCommand {
        if (!inputLine.hasNext()) {
            throw new InvalidCommand();
        }
        String commandGiven = inputLine.next();
        switch (commandGiven) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            return new AddCommand(commandGiven, inputLine);
        case "delete":
            // Fallthrough
        case "mark":
            // Fallthrough
        case "unmark":
            return new EditCommand(commandGiven, inputLine);
        case "find":
            return new FindCommand(inputLine);
        case "undo":
            return new UndoCommand();
        default:
            throw new InvalidCommand(commandGiven);
        }
    }
}
