package duke.parser;


import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * A parser to deal with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the input command and returns the corresponding command.
     *
     * @param fullCommand The given input command.
     * @return The corresponding command to be executed.
     * @throws DukeException If an error has occurred when creating a command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.trim().split(" ", 2);
        switch (inputs[0]) {
        // List out all tasks
        case ListCommand.COMMAND_WORD:
            return prepareListCommand(inputs);
        // Add todo task
        case TodoCommand.COMMAND_WORD:
            return prepareTodoCommand(inputs);
        // Add deadline task
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadlineCommand(inputs);
        // Add event task
        case EventCommand.COMMAND_WORD:
            return prepareEventCommand(inputs);
        // Mark task as done
        case MarkCommand.COMMAND_WORD:
            return prepareMarkCommand(inputs);
        // Mark task as undone
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmarkCommand(inputs);
        // Delete task
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(inputs);
        // Find tasks by keyword
        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(inputs);
        // Exits the application
        case ByeCommand.COMMAND_WORD:
            return prepareByeCommand(inputs);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n");
        }
    }

    private static ListCommand prepareListCommand(String[] inputs) throws DukeException {
        if (inputs.length == 2) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n");
        }
        return new ListCommand();
    }

    private static TodoCommand prepareTodoCommand(String[] inputs) throws DukeException {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        return new TodoCommand(inputs[1]);
    }

    private static DeadlineCommand prepareDeadlineCommand(String[] inputs) throws DukeException {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
        }

        String[] deadlineInputs = inputs[1].split(" /by ", 2);

        if (deadlineInputs.length == 1 || deadlineInputs[1].equals("")) {
            throw new DukeException("OOPS!!! The date of a deadline cannot be empty.\n");
        }

        try {
            LocalDateTime eventTiming = LocalDateTime.parse(deadlineInputs[1], Task.DATE_TIME_FORMATTER);
            return new DeadlineCommand(deadlineInputs[0], eventTiming);
        } catch (DateTimeException e) {
            throw new DukeException("OOPS!!! Invalid datetime format, input a valid date and time in the format "
                    + "YYYY-MM-DD HH:MM. \n");
        }
    }

    private static EventCommand prepareEventCommand(String[] inputs) throws DukeException {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
        }

        String[] eventInputs = inputs[1].split(" /at ", 2);

        if (eventInputs.length == 1 || eventInputs[1].equals("")) {
            throw new DukeException("OOPS!!! The date of an event cannot be empty.\n");
        }

        try {
            LocalDateTime eventTiming = LocalDateTime.parse(eventInputs[1], Task.DATE_TIME_FORMATTER);
            return new EventCommand(eventInputs[0], eventTiming);
        } catch (DateTimeException e) {
            throw new DukeException("OOPS!!! Invalid datetime format, input a valid date and time in the format "
                    + "YYYY-MM-DD HH:MM. \n");
        }
    }

    private static MarkCommand prepareMarkCommand(String[] inputs) throws DukeException {
        return (MarkCommand) updateTaskCompletionStatus(inputs, true);
    }

    private static UnmarkCommand prepareUnmarkCommand(String[] inputs) throws DukeException {
        return (UnmarkCommand) updateTaskCompletionStatus(inputs, false);
    }

    private static Command updateTaskCompletionStatus(String[] inputs, boolean isDone) {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The task index cannot be empty.\n");
        }

        try {
            // Tasks are stored as 0-index but display as 1-index
            // Minus 1 to get the correct task in the taskList
            int taskIndex = Integer.parseInt(inputs[1]) - 1;
            return isDone ? new MarkCommand(taskIndex) : new UnmarkCommand(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task index specified is not valid.\n");
        }
    }

    private static DeleteCommand prepareDeleteCommand(String[] inputs) throws DukeException {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The task index cannot be empty.\n");
        }

        try {
            // Tasks are stored as 0-index but display as 1-index
            // Minus 1 to get the correct task in the taskList
            int taskIndex = Integer.parseInt(inputs[1]) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The task index specified is not valid.\n");
        }
    }

    private static ByeCommand prepareByeCommand(String[] inputs) throws DukeException {
        if (inputs.length == 2) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n");
        }

        return new ByeCommand();
    }

    private static FindCommand prepareFindCommand(String[] inputs) {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The task description cannot be empty\n");
        }

        return new FindCommand(inputs[1]);
    }
}
