package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ModifyCommand;
import duke.exceptions.InvalidArgumentException;
import duke.exceptions.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Class to parse user input and return relevant command.
 */
public class Parser {

    /**
     * Processes user input for erroneous input and returns Command object.
     *
     * @param command Full command expressed as String
     * @return Command object corresponding to input command
     */
    public static Command parse(String command) {
        String currCmd = command.split(" ")[0];

        switch (currCmd) {
        case "bye":
            return createExitCommand(command);
        case "list":
            return createListCommand(command);
        case "mark":
            return createMarkCommand(command);
        case "unmark":
            return createUnmarkCommand(command);
        case "todo":
            return createTodoCommand(command);
        case "deadline":
            return createDeadlineCommand(command);
        case "event":
            return createEventCommand(command);
        case "delete":
            return createDeleteCommand(command);
        case "find":
            return createFindCommand(command);
        default:
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String getSecondHalf(String command) {
        return command.split(" ", 2)[1];
    }

    private static String[] getDescriptionAndDate(String secondHalf, String delimiter) {
        return secondHalf.split(delimiter);
    }

    private static ExitCommand createExitCommand(String command) {
        if (command.equals("bye")) {
            return new ExitCommand();
        }

        throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static ModifyCommand createListCommand(String command) {
        if (command.equals("list")) {
            return new ModifyCommand(ModifyCommand.CommandType.LIST);
        }

        throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }


    private static ModifyCommand createMarkCommand(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
            return new ModifyCommand(ModifyCommand.CommandType.DONE, taskNumber);
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! Please provide a valid number for this command");
        }
    }

    private static ModifyCommand createUnmarkCommand(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
            return new ModifyCommand(ModifyCommand.CommandType.UNDONE, taskNumber);
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! Please provide a valid number for this command");
        }
    }

    private static AddCommand createTodoCommand(String command) {
        try {
            String description = getSecondHalf(command);
            return new AddCommand(new Todo(description));
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static AddCommand createDeadlineCommand(String command) {
        try {
            String[] full = getDescriptionAndDate(getSecondHalf(command), " /by ");
            String description = full[0];
            String deadline = full[1];
            return new AddCommand(new Deadline(description, deadline));
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! Please format deadline request correctly.");
        }
    }

    private static AddCommand createEventCommand(String command) {
        try {
            String[] full = getDescriptionAndDate(getSecondHalf(command), " /at ");
            String description = full[0];
            String at = full[1];
            return new AddCommand(new Event(description, at));
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! Please format event request correctly.");
        }
    }

    private static DeleteCommand createDeleteCommand(String command) {
        try {
            int taskNumber = Integer.parseInt(command.split("\\s+")[1]) - 1;
            return new DeleteCommand(taskNumber);
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! Please provide a number for this command");
        }
    }

    private static FindCommand createFindCommand(String command) {
        try {
            String toFind = command.split(" ", 2)[1];
            return new FindCommand(toFind);
        } catch (Exception e) {
            throw new InvalidArgumentException("☹ OOPS!!! Please format find request correctly.");
        }
    }
}
