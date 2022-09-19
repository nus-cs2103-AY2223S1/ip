package duke.parser;

import duke.CommandWords;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Represents the parser of the Duke application.
 */
public class Parser {

    /**
     * Parses the input.
     * @param input Line of command that user has typed in.
     * @return Command for taking the next action.
     * @throws DukeException If the input is in the wrong format.
     */
    public static Command parse(String input) throws DukeException {
        CommandWords commandWord = getCommand(input);
        int startIndexOfDescription;
        switch (commandWord) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(getTaskNumber(4, input));
        case UNMARK:
            return new UnmarkCommand(getTaskNumber(6, input));
        case DELETE:
            return new DeleteCommand(getTaskNumber(6, input));
        case TODO:
            checkSeperatedBySpace(4, input);
            startIndexOfDescription = 6;
            if (input.length() < startIndexOfDescription) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task newTask = new Todo(input.substring(5));
            return new AddCommand(newTask);
        case EVENT:
            String[] eventDescDate = parseDescDate("/at", "event", input);
            return new AddCommand(new Event(eventDescDate[0], parseDate(eventDescDate[1])));
        case DEADLINE:
            String[] deadlineDescDate = parseDescDate("/by", "deadline", input);
            return new AddCommand(new Deadline(deadlineDescDate[0], parseDate(deadlineDescDate[1])));
        case FIND:
            checkSeperatedBySpace(4, input);
            startIndexOfDescription = 6;
            if (input.length() < startIndexOfDescription) {
                throw new DukeException("The keyword to search for cannot be empty.");
            }
            return new FindCommand(input.substring(5));
        case SORT:
            int commandLength = 4;
            if (input.length() == commandLength) {
                return new SortCommand();
            }
            if (input.equals("sort deadline")) {
                return new SortCommand("deadline");
            }
            if (input.equals("sort event")) {
                return new SortCommand("event");
            }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static CommandWords getCommand(String input) throws DukeException {
        if (input.equals("bye")) {
            return CommandWords.BYE;
        } else if (input.equals("list")) {
            return CommandWords.LIST;
        } else if (input.length() > 3 && input.substring(0, 4).equals("mark")) {
            return CommandWords.MARK;
        } else if (input.length() > 5 && input.substring(0, 6).equals("unmark")) {
            return CommandWords.UNMARK;
        } else if (input.length() > 3 && input.substring(0, 4).equals("todo")) {
            return CommandWords.TODO;
        } else if (input.length() > 7 && input.substring(0, 8).equals("deadline")) {
            return CommandWords.DEADLINE;
        } else if (input.length() > 4 && input.substring(0, 5).equals("event")) {
            return CommandWords.EVENT;
        } else if (input.length() > 5 && input.substring(0, 6).equals("delete")) {
            return CommandWords.DELETE;
        } else if (input.length() > 3 && input.substring(0, 4).equals("find")) {
            return CommandWords.FIND;
        } else if (input.length() > 3 && input.substring(0, 4).equals("sort")) {
            return CommandWords.SORT;
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void checkSeperatedBySpace(int commandLength, String input) throws DukeException {
        boolean hasSpace = input.charAt(commandLength) == ' ';
        if (!hasSpace) {
            throw new DukeException("Command and keyword should be separated by a space.");
        }
    }

    private static int getTaskNumber(int commandLength, String input) throws DukeException {
        boolean doesOnlyContainCommand = input.length() == commandLength;
        boolean doesOnlyContainCommandAndSpace = input.length() == commandLength + 1 &&
                input.charAt(commandLength) == ' ';
        if (doesOnlyContainCommand || doesOnlyContainCommandAndSpace) {
            throw new DukeException("Task number cannot be empty.");
        }
        checkSeperatedBySpace(commandLength, input);
        try {
            return Integer.parseInt(input.substring(commandLength + 1));
        } catch (NumberFormatException e) {
            throw new DukeException("Task number invalid.");
        }
    }

    private static String[] parseDescDate(String parser, String command, String input) throws DukeException{
        int commandLength = command.length();
        if (input.length() < commandLength + 2) {
            throw new DukeException("The description of a " + command + " cannot be empty.");
        }
        checkSeperatedBySpace(commandLength, input);
        String inputWithoutCommand = input.substring(commandLength + 1);
        String[] descDate = inputWithoutCommand.split(" " + parser + " ");
        boolean hasDate = descDate.length >= 2;
        boolean hasMoreThanOneDate = descDate.length > 2;
        if (!hasDate) {
            throw new DukeException(command + " date cannot be empty.");
        }
        if (hasMoreThanOneDate) {
            throw new DukeException("More than one " + command + " date entered.");
        }
        return descDate;
    }

    private static LocalDateTime parseDate(String dateTime) throws DukeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException exp) {
            throw new DukeException("Date and time of wrong format.");
        }
    }

}

