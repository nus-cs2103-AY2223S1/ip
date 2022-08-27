package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.BaseCommand;
import duke.commands.ExitCommand;
import duke.commands.tasks.AddTaskCommand;
import duke.commands.tasks.DeleteTaskCommand;
import duke.commands.tasks.ListTasksAfterCommand;
import duke.commands.tasks.ListTasksBeforeCommand;
import duke.commands.tasks.ListTasksCommand;
import duke.commands.tasks.MarkTaskCommand;
import duke.commands.tasks.UnmarkTaskCommand;
import duke.domain.Task;
import duke.domain.Todo;
import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.MissingArgumentException;
import duke.exceptions.NoCommandException;
import duke.exceptions.UnknownCommandException;

/**
 * Parser Class in charge of parsing the users' command.
 */
public class Parser {

    private static final String listCommand = "list";
    private static final String markCommand = "mark";
    private static final String todoCommand = "todo";
    private static final String deadlineCommand = "deadline";
    private static final String deadlineSubCommand = " /by ";
    private static final String eventCommand = "event";
    private static final String eventSubCommand = " /at ";
    private static final String advancedListSubCommand1 = "/before ";
    private static final String advancedListSubCommand2 = "/after ";
    private static final String unmarkCommand = "unmark";
    private static final String deleteCommand = "delete";
    private static final String exitCommand = "bye";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");

    private static LocalDateTime parseInputString(String inputDateTime)
            throws DateTimeParseException {
        return LocalDateTime.parse(inputDateTime, formatter);
    }

    /**
     * Returns a boolean corresponding to whether the given string is numeric.
     *
     * @param strNum
     *            String to be tested
     * @return boolean representing whether the string is numeric
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * The parse function takes a string as input and parses it into commands.
     * The parse function is responsible for parsing the user's input into
     * commands that can be executed by the program. It also handles any errors
     * that may occur during parsing, such as missing arguments or invalid date/time
     * formats.
     *
     * @param userInput
     *            Get the user input
     */
    public BaseCommand parse(String userInput)
            throws MissingArgumentException, InvalidDateTimeException, InvalidTaskSpecificationException,
            IncorrectArgumentException, UnknownCommandException, NoCommandException {
        if (userInput.trim().isEmpty()) {
            throw new NoCommandException(
                    "Please enter a command");
        }

        String[] commandArgs = userInput.split(" ");
        String[] commandArgsCopy = new String[commandArgs.length - 1];
        System.arraycopy(
                commandArgs,
                1,
                commandArgsCopy,
                0,
                commandArgs.length - 1);

        switch (commandArgs[0]) {
        case exitCommand:
            return new ExitCommand();
        case todoCommand:
            String todoText = String.join(" ", commandArgsCopy);

            if (commandArgsCopy.length > 0) {
                Task newTodo = Todo.of(todoText);
                return new AddTaskCommand(newTodo);
            } else {
                throw new MissingArgumentException(
                        "The description of the todo cannot be empty!");
            }
        case deadlineCommand:
            String deadlineText = String.join(" ", commandArgsCopy);
            if (deadlineText.contains(deadlineSubCommand)) {
                String[] deadlineArgs = deadlineText.split(
                        deadlineSubCommand);
                String deadlineTitle = deadlineArgs[0];
                String deadline = deadlineArgs[1];

                Task newDeadline = Task.of(
                        "D",
                        "0",
                        deadlineTitle,
                        deadline);
                return new AddTaskCommand(newDeadline);
            } else {
                throw new MissingArgumentException(
                        "Deadlines need a /by command");
            }
        case eventCommand:
            String eventText = String.join(" ", commandArgsCopy);
            if (eventText.contains(eventSubCommand)) {
                String[] eventArgs = eventText.split(eventSubCommand);
                String eventTitle = eventArgs[0];
                String eventDateTime = eventArgs[1];

                Task newEvent = Task.of(
                        "E",
                        "0",
                        eventTitle,
                        eventDateTime);

                return new AddTaskCommand(newEvent);
            } else {
                throw new MissingArgumentException(
                        "Events need a /at command");
            }
        case markCommand:
            if (isNumeric(commandArgs[1])) {
                int idx = Integer.parseInt(commandArgs[1]);
                return new MarkTaskCommand(idx - 1);
            } else {
                throw new IncorrectArgumentException(
                        "Sorry the second argument is not a number");
            }
        case unmarkCommand:
            if (isNumeric(commandArgs[1])) {
                int idx = Integer.parseInt(commandArgs[1]);
                return new UnmarkTaskCommand(idx - 1);
            } else {
                throw new IncorrectArgumentException(
                        "Sorry the second argument is not a number");
            }
        case deleteCommand:
            if (isNumeric(commandArgs[1])) {
                int idx = Integer.parseInt(commandArgs[1]);
                return new DeleteTaskCommand(idx - 1);
            } else {
                throw new IncorrectArgumentException(
                        "Sorry the second argument is not a number");
            }
        case listCommand:
            String advancedListText = String.join(" ", commandArgsCopy);
            if (advancedListText.contains(advancedListSubCommand1)) {
                String[] advancedListArgs = advancedListText.split(
                        advancedListSubCommand1);
                String advancedListDateTime = advancedListArgs[1];
                return new ListTasksBeforeCommand(
                        parseInputString(advancedListDateTime));
            } else if (advancedListText.contains(advancedListSubCommand2)) {
                String[] advancedListArgs = advancedListText.split(
                        advancedListSubCommand2);
                String advancedListDateTime = advancedListArgs[1];
                return new ListTasksAfterCommand(
                        parseInputString(advancedListDateTime));
            } else {
                return new ListTasksCommand();
            }
        default:
            throw new UnknownCommandException(
                    "Sorry I don't understand that command");
        }
    }
}
