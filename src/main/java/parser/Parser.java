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
import commands.SnoozeCommand;
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
        } else if (command.startsWith("snooze")) {
            return parseSnoozeCommand(command);
        } else {
            throw new FredException("I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Parse bye
     * @return ExitCommand
     */
    private static Command parseByeCommand() {
        return new ExitCommand();
    }

    /**
     * Parse list
     * @return ListCommand
     */
    private static Command parseListCommand() {
        return new ListCommand();
    }

    /**
     * Parse mark
     * @param command command string
     * @return MarkCommand
     * @throws FredException
     */
    private static Command parseMarkCommand(String command) throws FredException {
        if (command.trim().equals("mark")) {
            throw new FredException("The input of mark cannot be empty!");
        }

        int indexToMark;
        try {
            String commandWord = "mark";
            int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
            String commandArgument = command.substring(indexOfCommandArgument).trim();
            indexToMark = Integer.parseInt(commandArgument);
        } catch (NumberFormatException e) {
            throw new FredException("mark can only take in an integer!");
        }

        return new MarkCommand(indexToMark);
    }

    /**
     * Parse unmark
     * @param command command string
     * @return UnmarkCommand
     * @throws FredException
     */
    private static Command parseUnmarkCommand(String command) throws FredException {
        if (command.trim().equals("unmark")) {
            throw new FredException("The input of unmark cannot be empty!");
        }

        int indexToUnmark;
        try {
            String commandWord = "unmark";
            int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
            String commandArgument = command.substring(indexOfCommandArgument).trim();
            indexToUnmark = Integer.parseInt(commandArgument);
        } catch (NumberFormatException e) {
            throw new FredException("unmark can only take in an integer!");
        }

        return new UnmarkCommand(indexToUnmark);
    }

    /**
     * Parse todo
     * @param command command string
     * @return AddCommand for ToDo
     * @throws FredException
     */
    private static Command parseTodoCommand(String command) throws FredException {
        if (command.trim().equals("todo")) {
            throw new FredException("The description of a todo cannot be empty.");
        }

        String commandWord = "todo";
        int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
        String commandArgument = command.substring(indexOfCommandArgument).trim();
        return new AddCommand(TaskType.TODO, commandArgument);
    }

    /**
     * Parse event
     * @param command command string
     * @return AddCommand for Event
     * @throws FredException
     */
    private static Command parseEventCommand(String command) throws FredException {
        if (command.trim().equals("event")) {
            throw new FredException("The description of a event cannot be empty.");
        } else if (!command.contains("/at")) {
            throw new FredException("No date provided! Usage: \"TASK /at DATE\"");
        }

        String commandWord = "event";
        int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
        String commandArgument = command.substring(indexOfCommandArgument).trim();

        String[] commandArgumentParts = commandArgument.split(" /at ");
        String eventDescription = commandArgumentParts[0].trim();
        String eventDateString = commandArgumentParts[1].trim();

        LocalDate date;
        try {
            date = LocalDate.parse(eventDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new FredException("Input date as yyyy-MM-dd!");
        }
        return new AddCommand(TaskType.EVENT, eventDescription, date);
    }

    /**
     * Parse deadline
     * @param command command string
     * @return AddCommand for Deadline
     * @throws FredException
     */
    private static Command parseDeadlineCommand(String command) throws FredException {
        if (command.trim().equals("deadline")) {
            throw new FredException("The description of a deadline cannot be empty.");
        } else if (!command.contains("/by")) {
            throw new FredException("No date provided! Usage: \"TASK /by DATE\"");
        }

        String commandWord = "deadline";
        int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
        String commandArgument = command.substring(indexOfCommandArgument).trim();

        String[] commandArgumentParts = commandArgument.split(" /by ");
        String deadlineDescription = commandArgumentParts[0].trim();
        String deadlineDateString = commandArgumentParts[1].trim();

        LocalDate date;
        try {
            date = LocalDate.parse(deadlineDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new FredException("Input date as yyyy-MM-dd!");
        }
        return new AddCommand(TaskType.DEADLINE, deadlineDescription, date);
    }

    /**
     * Parse delete
     * @param command command string
     * @return DeleteCommand
     * @throws FredException
     */
    private static Command parseDeleteCommand(String command) throws FredException {
        if (command.trim().equals("delete")) {
            throw new FredException("The input of delete cannot be empty!");
        }

        int indexToDelete;
        try {
            String commandWord = "delete";
            int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
            String commandArgument = command.substring(indexOfCommandArgument).trim();
            indexToDelete = Integer.parseInt(commandArgument);
        } catch (NumberFormatException e) {
            throw new FredException("delete can only take in an integer!");
        }

        return new DeleteCommand(indexToDelete);
    }

    /**
     * Parse save
     * @return SaveCommand
     * @throws FredException
     */
    private static Command parseSaveCommand() throws FredException {
        return new SaveCommand();
    }

    /**
     * Parse find
     * @param command command string
     * @return FindCommand
     * @throws FredException
     */
    private static Command parseFindCommand(String command) throws FredException {
        if (command.trim().equals("find")) {
            throw new FredException("The input of find cannot be empty!");
        }

        String commandWord = "find";
        int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
        String commandArgument = command.substring(indexOfCommandArgument).trim();
        return new FindCommand(commandArgument);
    }

    /**
     * Parse snooze
     */
    private static Command parseSnoozeCommand(String command) throws FredException {
        if (command.trim().equals("snooze")) {
            throw new FredException("The input of snooze cannot be empty!");
        } else if (!command.contains("/to")) {
            throw new FredException("Wrong format! Usage: \"snooze TASKINDEX /to DATE\"");
        }

        String commandWord = "snooze";
        int indexOfCommandArgument = command.indexOf(commandWord) + commandWord.length() + 1;
        String commandArgument = command.substring(indexOfCommandArgument).trim();

        String[] commandArgumentParts = commandArgument.split(" /to ");
        String taskIndexString = commandArgumentParts[0].trim();
        String snoozeDateString = commandArgumentParts[1].trim();

        int taskIndex;
        LocalDate date;
        try {
            taskIndex = Integer.parseInt(taskIndexString);
            date = LocalDate.parse(snoozeDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (NumberFormatException e) {
            throw new FredException("TASKINDEX must be an integer");
        } catch (DateTimeParseException e) {
            throw new FredException("Input date as yyyy-MM-dd!");
        }

        return new SnoozeCommand(taskIndex, date);
    }
}
