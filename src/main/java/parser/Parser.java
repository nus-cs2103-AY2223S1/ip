package parser;

import command.*;

import exception.DorisException;

import java.time.format.DateTimeParseException;

import tasklist.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String command) throws DorisException {
        String[] commands = command.split(" ", 2);
        DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        switch (commands[0]) {
            case "todo":
                if (command.length() <= 5) {
                    throw new DorisException("Oi don't anyhow type must enter a task to do leh");
                }
                return new AddCommand(commands[1]);
            case "deadline":
                if (command.length() <= 9) {
                    throw new DorisException("Oi don't anyhow type must enter a task to do leh");
                }
                String[] description = commands[1].split(" /by ");
                return new AddCommand("deadline", description[0], parseDateTime(description[1], DF));
            case "event":
                if (command.length() <= 6) {
                    throw new DorisException("Oi don't anyhow type must enter a task to do leh");
                }
                description = commands[1].split(" /at ");
                return new AddCommand("event", description[0], parseDateTime(description[1], DF));
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(parseNum(commands[1]));
            case "mark":
                return new MarkCommand(parseNum(commands[1]));
            case "unmark":
                return new UnmarkCommand(parseNum(commands[1]));
            case "help":
                return new HelpCommand();
            default:
                throw new DorisException("Eh what are you talking can speak properly or not");
        }
    }

    public static Task parseSaved(String task) throws DorisException {
        String[] commands = task.split(" \\| ");
        DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        switch (commands[0]) {
            case "T":
                return new Todo(commands[1]);
            case "D":
                return new Deadline(commands[1], parseDateTime(commands[2], DF));
            case "E":
                return new Event(commands[1], parseDateTime(commands[2], DF));
            default:
                throw new DorisException("I have issues reading your file can save properly or not");
        }
    }

    public static int parseNum(String numString) throws DorisException {
        int num;
        try {
            num = Integer.parseInt(numString);
            if (num > TaskList.size()) {
                throw new DorisException("Oi the number too big la try again");
            }
        } catch (NumberFormatException e) {
            throw new DorisException("Eh that's not a number leh try again");
        }
        return num;
    }

    public static LocalDateTime parseDateTime(String dateTime, DateTimeFormatter DF) throws DorisException {
        try {
            return LocalDateTime.parse(dateTime, DF);
        } catch (DateTimeParseException e) {
            throw new DorisException("The date format wrong la try again");
        }
    }
}
