package duke;

import duke.command.*;

import java.time.LocalDate;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] inputArray = fullCommand.split(" +", 2);
        String firstWord = inputArray[0];
        String argsString = "";
        if (inputArray.length == 2) {
            argsString = inputArray[1];
        }
        // commands
        switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(parseInt(argsString));
            case "unmark":
                return new UnmarkCommand(parseInt(argsString));
            case "todo":
                return new AddCommand(parseTodo(argsString));
            case "deadline":
                return new AddCommand(parseDeadline(argsString));
            case "event":
                return new AddCommand(parseEvent(argsString));
            case "delete":
                return new DeleteCommand(parseInt(argsString));
            case "on":
                return new TaskOnDateCommand(LocalDate.parse(argsString));
            default:
                throw new DukeException("I don't know this command!");
        }
    }

    public static Todo parseTodo(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public static Deadline parseDeadline(String argsString) throws DukeException {
        String[] args = argsString.split(" */by *");
        if (args.length != 2) {
            throw new DukeException("Wrong usage of deadline.\nUsage: deadline some description /by some date");
        }
        String description = args[0];
        String by = args[1];
        LocalDate byDate = LocalDate.parse(by);
        if (description.length() == 0) {
            throw new DukeException("Description should not be empty.");
        }

        return new Deadline(description, byDate);
    }

    public static Event parseEvent(String argsString) throws DukeException {
        String[] args = argsString.split(" */at *");
        if (args.length != 2) {
            throw new DukeException("Wrong usage of event.\nUsage: event some description /at some date");
        }
        String description = args[0];
        if (description.length() == 0) {
            throw new DukeException("Description should not be empty.");
        }
        String at = args[1];
        LocalDate atDate = LocalDate.parse(at);
        return new Event(description, atDate);
    }

    public static int parseInt(String number) throws DukeException {
        try {
            return Integer.parseInt(number);
        } catch (java.lang.NumberFormatException e) {
            throw new DukeException(String.format("%s is not a number. e.g 5 is a number, five is a string.", number));
        }
    }
}
