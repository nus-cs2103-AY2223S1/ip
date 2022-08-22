package Duke;

import Commands.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input) throws DukeException {
        String[] components = input.split(" ", 2);
        switch (components[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            checkInput(components);
            return new MarkCommand(Integer.parseInt(components[1]));
        case "unmark":
            checkInput(components);
            return new UnmarkCommand(Integer.parseInt(components[1]));
        case "todo":
            checkInput(components);
            return new TodoCommand(components[1]);
        case "deadline":
            checkInput(components);
            String[] deadlineInfo = components[1].split(" /by ");
            checkInput(deadlineInfo);
            checkDate(deadlineInfo[1]);
            return new DeadlineCommand(deadlineInfo[0], deadlineInfo[1]);
        case "event":
            checkInput(components);
            String[] eventInfo = components[1].split(" /at ");
            checkInput(eventInfo);
            checkDate(eventInfo[1]);
            return new EventCommand(eventInfo[0], eventInfo[1]);
        case "delete":
            checkInput(components);
            return new DeleteCommand(Integer.parseInt(components[1]));
        case "tasks":
            checkInput(components);
            checkDate(components[1]);
            return new TasksCommand(components[1]);
        default:
            return new InvalidCommand();
        }
    }

    public static void checkInput(String[] input) throws DukeException {
        if (input.length <= 1) {
            throw new DukeException("Invalid input!");
        }
    }

    public static void checkDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("The date included should follow this format: dd/MM/yyyy");
        }
    }
}
