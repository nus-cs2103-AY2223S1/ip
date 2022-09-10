package duke.parser;


import duke.commands.*;
import duke.exception.DukeException;
import duke.task.Task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputs = fullCommand.trim().split(" ", 2);
        switch (inputs[0]) {
        // List out all tasks
        case ListCommand.COMMAND_WORD:
            if (inputs.length == 2) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n");
            }
            return new ListCommand();
        // Add todo duke.task
        case TodoCommand.COMMAND_WORD:
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
            }
            return new TodoCommand(inputs[1]);
        // Add deadline duke.task
        case DeadlineCommand.COMMAND_WORD:
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
                throw new DukeException("OOPS!!! Invalid datetime format, input a valid date and time in the format " +
                        "YYYY-MM-DD HH:MM. \n");
            }
        // Add event duke.task
        case EventCommand.COMMAND_WORD:
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
                throw new DukeException("OOPS!!! Invalid datetime format, input a valid date and time in the format " +
                    "YYYY-MM-DD HH:MM. \n");
            }
        // Mark duke.task as done
        case MarkCommand.COMMAND_WORD:
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeException("OOPS!!! The duke.task index cannot be empty.\n");
            }

            try {
                // Tasks are stored as 0-index but display as 1-index
                // Minus 1 to get the correct duke.task in the taskList
                int taskIndex = Integer.parseInt(inputs[1]) - 1;
                return new MarkCommand(taskIndex);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The duke.task index specified is not valid.\n");
            }
        // Mark duke.task as undone
        case UnmarkCommand.COMMAND_WORD:
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeException("OOPS!!! The duke.task index cannot be empty.\n");
            }

            try {
                // Tasks are stored as 0-index but display as 1-index
                // Minus 1 to get the correct duke.task in the taskList
                int taskIndex = Integer.parseInt(inputs[1]) - 1;
                return new UnmarkCommand(taskIndex);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The duke.task index specified is not valid.\n");
            }
        // Delete duke.task
        case DeleteCommand.COMMAND_WORD:
            if (inputs.length == 1 || inputs[1].equals("")) {
                throw new DukeException("OOPS!!! The duke.task index cannot be empty.\n");
            }

            try {
                // Tasks are stored as 0-index but display as 1-index
                // Minus 1 to get the correct duke.task in the taskList
                int taskIndex = Integer.parseInt(inputs[1]) - 1;
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The duke.task index specified is not valid.\n");
            }
        // Find a task by searching for a keyword
        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(inputs);
        case ByeCommand.COMMAND_WORD:
            if (inputs.length == 2) {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n");
            }

            return new ByeCommand();
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(.\n");
        }
    }

    public static FindCommand prepareFindCommand(String[] inputs) {
        if (inputs.length == 1 || inputs[1].equals("")) {
            throw new DukeException("OOPS!!! The task description cannot be empty\n");
        }

        return new FindCommand(inputs[1]);
    }
}
