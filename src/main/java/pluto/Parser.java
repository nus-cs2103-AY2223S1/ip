package pluto;

import pluto.command.*;
import pluto.task.Deadline;
import pluto.task.Todo;
import pluto.task.Event;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;

public class Parser {
    enum Type {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        SHOW,
        BYE
    }

    public static Command parse(String inputLine) throws PlutoException {

        isOnlyCommand(inputLine.toLowerCase());
        String[] textArr = inputLine.split(" ", 2);
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
            case BYE:
                return new ExitCommand();
            default:
                throw new PlutoException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

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
                throw new PlutoException("\tOOPS!!! pluto.task.Task must be a pluto.task.Todo, pluto.task.Deadline or pluto.task.Event.");
        }
    }

    public static void isOnlyCommand(String str) throws PlutoException {
        HashSet<String> commands = new HashSet<>(Arrays.asList("todo", "deadline", "event", "mark", "unmark", "delete", "show"));
        if (commands.contains(str.strip())) {
            throw new PlutoException(String.format("\tOOPS!!! The description of %s cannot be empty.", str));
        }
    }

    public static LocalDateTime parseDate(String date) throws PlutoException {
        try {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new PlutoException("\tOOPS!!! dd-MM-yyyy HHmm date format required.");
        }
    }

    public static LocalDate parseDateOnly(String date) throws PlutoException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new PlutoException("\tOOPS!!! dd-MM-yyyy date format required.");
        }
    }

    public static int parseIdx(String idx) throws PlutoException {
        try {
            return Integer.parseInt(idx.strip()) - 1;
        } catch (NumberFormatException e) {
            throw new PlutoException("\tOOPS!!! Index of task is required.");
        }
    }
}
