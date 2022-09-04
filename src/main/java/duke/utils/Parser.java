package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Handles the parsing of user input.
 */
public class Parser {
    private static final InvalidCommand NUMBER_FORMAT = new InvalidCommand("Task index should be integers!");

    /**
     * Parses the user input and returns the appropriate Command.
     *
     * @param userInput Raw user input
     * @return Command based on user input
     */
    public Command parseCommand(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        String command = tokens[0];

        switch (command) {
        case AddCommand.COMMAND_WORD:
            return prepareAdd(userInput);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(userInput);
        case MarkCommand.COMMAND_WORD:
            return prepareMark(userInput);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(userInput);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return prepareFind(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new InvalidCommand("Sorry, I wasn't programmed to understand this...");
        }
    }

    /**
     * Prepares and returns a new AddCommand.
     * This method parses the user input to distinguish between the different Tasks
     * and returns an AddCommand with the correct Task.
     *
     * @param userInput Raw user input
     * @return AddCommand based on raw user input
     */
    private Command prepareAdd(String userInput) {
        String[] tokens = userInput.split(" ", 3);
        if (tokens.length < 3) {
            return new InvalidCommand("Oh no! Try add <task type> <description>!");
        }

        String taskType = tokens[1].toLowerCase();

        switch (taskType) {
        case Todo.TASK_WORD:
            String todoDescription = tokens[2].trim();
            Todo newTodo = new Todo(todoDescription, false);
            return new AddCommand(newTodo);
        case Deadline.TASK_WORD:
            try {
                String[] deadlineTokens = tokens[2].split("/by");
                if (deadlineTokens.length < 2) {
                    return new InvalidCommand("Oh no! Try doing 'add deadline <description> /by " + "<date>'!");
                }
                String deadlineDescription = deadlineTokens[0].trim();
                LocalDateTime by = parseDateTime(deadlineTokens[1].trim());
                Deadline newDeadline = new Deadline(deadlineDescription, false, by);
                return new AddCommand(newDeadline);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        case Event.TASK_WORD:
            try {
                String[] eventTokens = tokens[2].split("/at");
                if (eventTokens.length < 2) {
                    return new InvalidCommand("Oh no! Try doing 'add event <description> /at" + " <date>'!");
                }
                String eventDescription = eventTokens[0].trim();
                LocalDateTime at = parseDateTime(eventTokens[1].trim());
                Event newEvent = new Event(eventDescription, false, at);
                return new AddCommand(newEvent);
            } catch (DukeException e) {
                return new InvalidCommand(e.getMessage());
            }
        default:
            return new InvalidCommand("There's no such event type as " + taskType + "!");
        }
    }

    /**
     * Prepares and returns a new DeleteCommand.
     *
     * @param userInput Raw user input
     * @return DeleteCommand based on raw user input
     */
    private Command prepareDelete(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try delete <task index>!");
        }
        try {
            int taskIndex = Integer.parseInt(tokens[1], 10) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT;
        }
    }

    /**
     * Prepares and returns a new MarkCommand.
     *
     * @param userInput Raw user input
     * @return MarkCommand based on raw user input
     */
    private Command prepareMark(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try delete <mark index>!");
        }
        try {
            int taskIndex = Integer.parseInt(tokens[1], 10) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT;
        }
    }

    /**
     * Prepares and returns a new UnmarkCommand.
     *
     * @param userInput Raw user input
     * @return UnmarkCommand based on raw user input
     */
    private Command prepareUnmark(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try delete <unmark index>!");
        }
        try {
            int taskIndex = Integer.parseInt(tokens[1], 10) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            return NUMBER_FORMAT;
        }
    }

    private Command prepareFind(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2) {
            return new InvalidCommand("Oh no! Try find <keyword>!");
        }
        return new FindCommand(tokens[1]);
    }

    /**
     * Creates and returns LocalDate objects by parsing user's input for date.
     * User input date should follow the format (yyyy-mm-dd HH:MM), in 24-hour time format.
     * The date can have a delimiter of a character from "-/|" (characters within the double quotes).
     * The time can have a delimiter of a character from ":.-|" (characters within the double quotes).
     * The date and time must be separated by a single whitespace.
     *
     * @param dateText User's input of date and time following the format specified.
     * @return LocalDateTime
     */
    private static LocalDateTime parseDateTime(String dateText) throws DukeException {
        final DukeException wrongFormat = new DukeException("Date and time is in the wrong format! "
                + "Correct format: yyyy-mm-dd HH:MM");
        final DukeException cannotParse = new DukeException("Date and time may have invalid values!");

        String[] dateTextTokens = dateText.split(" ");
        if (dateTextTokens.length != 2) {
            throw wrongFormat;
        }

        String unparsedDate = dateTextTokens[0];
        String[] dateTokens = unparsedDate.split("[-./|]");
        if (dateTokens.length != 3) {
            throw wrongFormat;
        }
        if (dateTokens[0].length() != 4 || dateTokens[1].length() != 2 || dateTokens[2].length() != 2) {
            throw wrongFormat;
        }
        String parsedDate = String.join("-", dateTokens);
        assert parsedDate.split("-").length == 3;

        String unparsedTime = dateTextTokens[1];
        String[] timeTokens = unparsedTime.split("[-:.|]");

        if (timeTokens.length != 2) {
            throw wrongFormat;
        }
        if (timeTokens[0].length() != 2 || timeTokens[1].length() != 2) {
            throw wrongFormat;
        }
        String parsedTime = String.join(":", timeTokens);
        assert parsedTime.split(":").length == 2;


        String parsedDateTime = parsedDate + "T" + parsedTime;
        assert parsedDateTime.split("T").length == 2;
        try {
            return LocalDateTime.parse(parsedDateTime);
        } catch (DateTimeParseException e) {
            throw cannotParse;
        }
    }
}
