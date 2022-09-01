package duke;

import java.util.Objects;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateMarkCommand;
import duke.command.UpdateUnmarkCommand;

/**
 * Contains methods to parse user input.
 */
public abstract class Parser {
    /**
     * Parses the input string from user and if valid, returns a Command,
     * else throws IllegalArgumentException.
     *
     * @param commandString input string from user.
     * @param taskList TaskList containing current tasks.
     * @return Command instance representing the commandString.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static Command parse(String commandString, TaskList taskList) throws IllegalArgumentException {
        String[] commandArr = commandString.split(" ");
        Command.Commands commandWord = null;
        Command command = null;
        try {
            commandWord = Command.Commands.valueOf(commandArr[0]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }

        if (!Objects.isNull(commandWord)) {
            switch(commandWord) {
            case mark:
                command = UpdateMarkCommand.of(commandString, taskList);
                break;
            case unmark:
                command = UpdateUnmarkCommand.of(commandString, taskList);
                break;
            case todo:
                command = AddTodoCommand.of(commandString);
                break;
            case deadline:
                command = AddDeadlineCommand.of(commandString);
                break;
            case event:
                command = AddEventCommand.of(commandString);
                break;
            case delete:
                command = DeleteCommand.of(commandString, taskList);
                break;
            case bye:
                command = new ByeCommand(commandString);
                break;
            case list:
                command = new ListCommand(commandString);
                break;
            case find:
                command = FindCommand.of(commandString);
                break;
            default:
                break; // should not occur, since commandWord can only be null or Commands
            }
        }

        return command;
    }

    /**
     * Parses the second word of the input string from user as an integer, and returns -1 of that value
     *
     * @param command input string from user.
     * @return integer representing a 0-based task index from the 1-based task index given.
     * @throws IllegalArgumentException if second word is not an integer.
     */
    public static int getTaskIndex(String command) throws IllegalArgumentException {
        int i;
        String[] commandArr = command.split(" ");
        try {
            i = Integer.parseInt(commandArr[1]);
        } catch (NumberFormatException e) { // if second word not integer
            throw new IllegalArgumentException();
        }

        return i - 1;
    }
}
