package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;
import duke.commands.UserCommandList;

import java.util.Scanner;

public class Parser {
    /**
     * Parse user input and returns the command to be executed.
     *
     * @param userInput User input.
     * @return Command to be executed.
     */
    public static Command parseUserInput(Scanner userInput) {
        UserCommandList command;
        try {
            command = UserCommandList.valueOf(userInput.next().toUpperCase());
        } catch (IllegalArgumentException iae) {
            return new IncorrectCommand(userInput);
        }
        switch(command) {
        case TODO:
            return new ToDoCommand(userInput);
        case DEADLINE:
            return new DeadlineCommand(userInput);
        case EVENT:
            return new EventCommand(userInput);
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(userInput);
        case UNMARK:
            return new UnmarkCommand(userInput);
        case DELETE:
            return new DeleteCommand(userInput);
        case FIND:
            return new FindCommand(userInput);
        case BYE:
            return new ByeCommand();
        default:
            throw new AssertionError("Should not reach this stage.");
        }
    }
}
