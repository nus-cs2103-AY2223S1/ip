package kohaku.parser;

import kohaku.commands.*;
import kohaku.datastruct.TaskList;
import kohaku.daveexceptions.DaveException;
import kohaku.datastruct.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter slashFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final DateTimeFormatter dashFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Calls the appropriate command given the input command word.
     *
     * @param command String representation of command to be called
     * @param args Arguments for the command
     * @param tasks List of tasks that is required for the command
     * @return The command to be called
     * @throws DaveException throws an exception if the command is not recognisable
     */
    public static Command dispatch(String command, String args, TaskList tasks) throws DaveException {
        switch (command) {
        case "hi":
            return new GreetCommand();
        case "bye":
            return new EndCommand(tasks, args);
        case "list":
            return new ListCommand(tasks, args);
        case "mark":
            return new MarkDoneCommand(tasks, args);
        case "unmark":
            return new UnmarkDoneCommand(tasks, args);
        case "todo":
            return new AddTodoCommand(tasks, args);
        case "deadline":
            return new AddDeadlineCommand(tasks, args);
        case "event":
            return new AddEventCommand(tasks, args);
        case "remove":
            return new RemoveTaskCommand(tasks, args);
        case "find":
            return new FindTasksCommand(tasks, args);
        case "archive":
            return new ArchiveCommand(tasks, args);
        case "load":
            return new LoadCommand(tasks, args);
        default:
            throw new DaveException("I'm sowwy, but I don't know what that means! ;~;");
        }
    }


    /**
     * Splits the raw input into the command and its arguments.
     *
     * @param input Raw input containing a command and its arguments
     * @return Pair containing a command and its arguments
     */
    public static Pair<String, String> splitInputIntoCommand(String input) {
        String[] splitInput = input.trim().split(" ", 2);
        String command = splitInput[0].toLowerCase();
        String args;
        try {
            args = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            args = "";
        }
        assert args != null : "Arguments to a command should be a string and never null";
        return new Pair<String, String>(command, args);
    }

    /**
     * Parses the String input representing an Event or Deadline by checking its validity and returning
     * a pair containing the string representation of the task and its time if valid.
     *
     * @param input String representation of an Event or Deadline
     * @return Pair containing the string representation of the task and its time
     * @throws DaveException throws an exception if the input is invalid
     */
    public static Pair<String, LocalDateTime> parseTask(String input) throws DaveException {
        if (input.equals("")) {
            throw new DaveException("Oh no!!! The description of an event cannot be empty!");
        }
        String[] args = input.split("/at |/by ");
        if (args.length > 2) {
            throw new DaveException("Oh no!!! Too many timings given, Kohaku's brain is fried ;~;");
        } else if (args.length < 2) {
            throw new DaveException("Oh no!!! Please provide a timing for the event!");
        }

        assert args.length == 2 : "Number of args to a Task command should always be 2";

        String task = args[0];
        String dateStr = args[1].trim();
        LocalDateTime dateTime;

        try {
            if (dateStr.contains("/")) {
                dateTime = LocalDateTime.parse(dateStr, slashFormat);
            } else {
                dateTime = LocalDateTime.parse(dateStr, dashFormat);
            }
        } catch (DateTimeParseException e) {
            throw new DaveException("Please input a valid date! >~<");
        }

        return new Pair<>(task, dateTime);
    }
}