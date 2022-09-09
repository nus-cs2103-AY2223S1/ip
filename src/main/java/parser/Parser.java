package parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.SaveCommand;
import commands.UnmarkCommand;
import exception.FredException;
import task.TaskType;


/**
 * Parser parses through the command string given by user and returns the correct command
 */
public class Parser {

    /**
     * Parses command string and return the parsed Command object for further execution of program.
     * @param command Command string to be parsed by parser.
     * @return Command that is form the parsed command string.
     * @throws FredException
     */
    public static Command parse(String command) throws FredException {
        if (command.equals("bye")) {
            return parseByeCommand();
        } else if (command.equals("list")) {
            return parseListCommand();
        } else if (command.startsWith("mark")) {
            return parseMarkCommand(command);
        } else if (command.startsWith("unmark")) {
            return parseUnmarkCommand(command);
        } else if (command.startsWith("todo")) {
            return parseTodoCommand(command);
        } else if (command.startsWith("event")) {
            return parseEventCommand(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith("delete")) {
            return parseDeleteCommand(command);
        } else if (command.equals("save")) {
            return parseSaveCommand();
        } else if (command.startsWith("find")) {
            return parseFindCommand(command);
        } else {
            throw new FredException("I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Parse bye
     * @return ExitCommand
     */
    public static Command parseByeCommand() {
        return new ExitCommand();
    }

    /**
     * Parse list
     * @return ListCommand
     */
    public static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parse mark
     * @param command command string
     * @return MarkCommand
     * @throws FredException
     */
    public static Command parseMarkCommand(String command) throws FredException {
        if (command.trim().equals("mark")) {
            throw new FredException("The input of mark cannot be empty!");
        }

        int index;
        try {
            index = Integer.parseInt(command.substring(5));
        } catch (NumberFormatException e) {
            throw new FredException("mark can only take in an integer!");
        }

        return new MarkCommand(index);
    }

    /**
     * Parse unmark
     * @param command command string
     * @return UnmarkCommand
     * @throws FredException
     */
    public static Command parseUnmarkCommand(String command) throws FredException {
        if (command.trim().equals("unmark")) {
            throw new FredException("The input of unmark cannot be empty!");
        }

        int index;
        try {
            index = Integer.parseInt(command.substring(7));
        } catch (NumberFormatException e) {
            throw new FredException("unmark can only take in an integer!");
        }

        return new UnmarkCommand(index);
    }

    /**
     * Parse todo
     * @param command command string
     * @return AddCommand for ToDo
     * @throws FredException
     */
    public static Command parseTodoCommand(String command) throws FredException {
        if (command.trim().equals("todo")) {
            throw new FredException("The description of a todo cannot be empty.");
        }
        return new AddCommand(TaskType.TODO, command.substring(5));
    }

    /**
     * Parse event
     * @param command command string
     * @return AddCommand for Event
     * @throws FredException
     */
    public static Command parseEventCommand(String command) throws FredException {
        if (command.trim().equals("event")) {
            throw new FredException("The description of a event cannot be empty.");
        }
        int split = command.indexOf("/at");
        if (split == -1) {
            throw new FredException("No date provided! Usage: \"TASK /at DATE\"");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(command.substring(split + 4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new FredException("Input date as yyyy-MM-dd!");
        }
        return new AddCommand(TaskType.EVENT, command.substring(6, split - 1), date);
    }

    /**
     * Parse deadline
     * @param command command string
     * @return AddCommand for Deadline
     * @throws FredException
     */
    public static Command parseDeadlineCommand(String command) throws FredException {
        if (command.trim().equals("deadline")) {
            throw new FredException("The description of a deadline cannot be empty.");
        }
        int split = command.indexOf("/by");
        if (split == -1) {
            throw new FredException("No date provided! Usage: \"TASK /by DATE\"");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(command.substring(split + 4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new FredException("Input date as yyyy-MM-dd!");
        }
        return new AddCommand(TaskType.DEADLINE, command.substring(9, split - 1), date);
    }

    /**
     * Parse delete
     * @param command command string
     * @return DeleteCommand
     * @throws FredException
     */
    public static Command parseDeleteCommand(String command) throws FredException {
        if (command.trim().equals("delete")) {
            throw new FredException("The input of delete cannot be empty!");
        }

        int index;
        try {
            index = Integer.parseInt(command.substring(7));
        } catch (NumberFormatException e) {
            throw new FredException("delete can only take in an integer!");
        }

        return new DeleteCommand(index);
    }

    /**
     * Parse save
     * @return SaveCommand
     * @throws FredException
     */
    public static Command parseSaveCommand() throws FredException {
        return new SaveCommand();
    }

    /**
     * Parse find
     * @param command command string
     * @return FindCommand
     * @throws FredException
     */
    public static Command parseFindCommand(String command) throws FredException {
        if (command.trim().equals("find")) {
            throw new FredException("The input of delete cannot be empty!");
        }
        return new FindCommand(command.substring(5));
    }
}
