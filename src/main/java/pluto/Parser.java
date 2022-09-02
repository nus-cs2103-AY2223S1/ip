package pluto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;

import pluto.command.AddCommand;
import pluto.command.Command;
import pluto.command.DeleteCommand;
import pluto.command.ExitCommand;
import pluto.command.FindCommand;
import pluto.command.ListCommand;
import pluto.command.ShowCommand;
import pluto.command.UpdateStatusCommand;
import pluto.task.Deadline;
import pluto.task.Event;
import pluto.task.Todo;

/**
 * Parses commands input by the user.
 */
public class Parser {
    /** Types of commands */
    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        SHOW,
        FIND,
        BYE
    }

    /**
     * Parses the string input to meaningful commands.
     * @param inputLine Input by the user.
     * @return Command to be executed.
     * @throws PlutoException If inputLine cannot be parsed properly.
     */
    public static Command parse(String inputLine) throws PlutoException {

        isOnlyCommand(inputLine.toLowerCase());
        String[] textArr = inputLine.split("\\s+", 2);
        String command = textArr[0].strip();
        Type enumCommand;
        try {
            enumCommand = Type.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new PlutoException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        switch (enumCommand) {
        case TODO:
            return parseTask(textArr[1].strip(), Type.TODO);
        case DEADLINE:
            return parseTask(textArr[1].strip(), Type.DEADLINE);
        case EVENT:
            return parseTask(textArr[1].strip(), Type.EVENT);
        case MARK:
            return new UpdateStatusCommand(parseIdx(textArr[1]), true);
        case UNMARK:
            return new UpdateStatusCommand(parseIdx(textArr[1]), false);
        case DELETE:
            return new DeleteCommand(parseIdx(textArr[1]));
        case LIST:
            return new ListCommand();
        case SHOW:
            return new ShowCommand(parseDateOnly(textArr[1]));
        case FIND:
            return new FindCommand(textArr[1]);
        case BYE:
            return new ExitCommand();
        default:
            throw new PlutoException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a string to the corresponding addCommand Task.
     * @param input Input by the user.
     * @param type Type of task.
     * @return Corresponding AddCommand.
     * @throws PlutoException If input cannot be parsed properly.
     */
    public static Command parseTask(String input, Type type) throws PlutoException {
        switch (type) {
        case TODO:
            return new AddCommand(new Todo(input));
        case DEADLINE:
            String[] arrDeadline = input.split("/by", 2);
            if (arrDeadline.length == 1) {
                throw new PlutoException("\tOOPS!!! The deadline date is required.");
            }
            return new AddCommand(new Deadline(arrDeadline[0].strip(), parseDate(arrDeadline[1].strip())));
        case EVENT:
            String[] arrEvent = input.split("/at", 2);
            if (arrEvent.length == 1) {
                throw new PlutoException("\tOOPS!!! The event date is required.");
            }
            return new AddCommand(new Event(arrEvent[0].strip(), parseDate(arrEvent[1].strip())));
        default:
            throw new PlutoException("\tOOPS!!! Task must be a Todo, Deadline or Event.");
        }
    }

    /**
     * Checks whether command description is missing or not.
     * @param str Input by the user.
     * @throws PlutoException If str doesn't have a description.
     */
    public static void isOnlyCommand(String str) throws PlutoException {
        HashSet<String> commands = new HashSet<>(Arrays.asList("todo", "deadline", "event", "mark",
                "unmark", "delete", "show"));
        if (commands.contains(str.strip())) {
            throw new PlutoException(String.format("\tOOPS!!! The description of %s cannot be empty.", str));
        }
    }

    /**
     * Parses a string to a LocalDateTime instance.
     * @param date Date string.
     * @return LocalDateTime instance.
     * @throws PlutoException If date format is incorrect.
     */
    public static LocalDateTime parseDate(String date) throws PlutoException {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            System.out.println(date);
            throw new PlutoException("\tOOPS!!! dd-MM-yyyy HHmm date format required.");
        }
    }

    /**
     * Parses a string to a LocalDate instance.
     * @param date Date string.
     * @return LocalDate instance.
     * @throws PlutoException If date format is incorrect.
     */
    public static LocalDate parseDateOnly(String date) throws PlutoException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new PlutoException("\tOOPS!!! dd-MM-yyyy date format required.");
        }
    }

    /**
     * Parses an string of integer to an integer.
     * @param idx String of integer.
     * @return Integer value of the string.
     * @throws PlutoException If idx string has characters other than numbers.
     */
    public static int parseIdx(String idx) throws PlutoException {
        try {
            return Integer.parseInt(idx.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new PlutoException("\tOOPS!!! Index of task is required.");
        }
    }
}
