package mykoba;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnMarkCommand;
import exception.KobaException;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * This class handles the parsing of user inputs.
 */
public class Parser {

    /**
     * Method that takes in a String of user input and returns a specific
     * type of command to achieve the task specified.
     *
     * @param input The line of text inputted by the user.
     * @return A Command that performs the task specified by the user.
     * @throws KobaException If input is invalid.
     */
    public static Command parse(String input) throws KobaException {
        String[] words = input.trim().split(" ", 2);
        String command = words[0].trim();
        String description = words.length > 1 ? words[1].trim() : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "mark":
            return getMarkCommand(description);
        case "unmark":
            return getUnMarkCommand(description);
        case "todo":
            return AddToDo(description);
        case "deadline":
            return AddDeadline(description);
        case "event":
            return AddEvent(description);
        case "delete":
            return getDeleteCommand(description);
        case "find":
            return getFindCommand(description);
        default:
            throw new KobaException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static MarkCommand getMarkCommand(String description) {
        int index = Integer.parseInt(description) - 1;
        return new MarkCommand(index);
    }

    private static UnMarkCommand getUnMarkCommand(String description) {
        int index = Integer.parseInt(description) - 1;
        return new UnMarkCommand(index);
    }

    private static DeleteCommand getDeleteCommand(String description) {
        int index = Integer.parseInt(description) - 1;
        return new DeleteCommand(index);
    }

    private static AddCommand AddToDo(String description) throws KobaException {
        if (description.length() == 0) {
            throw new KobaException("Description cannot be empty!");
        } else {
            return new AddCommand(new ToDo(description, false));
        }
    }

    private static AddCommand AddDeadline(String description) throws KobaException {
        String[] splitString = description.split(" /by ", 2);
        if (splitString.length <= 1) {
            throw new KobaException("The date cannot be empty!");
        }
        String task = splitString[0].trim();
        String dateby = splitString[1].trim();
        try {
            LocalDateTime date = LocalDateTime.parse(dateby);
            return new AddCommand(new Deadline(task, false, date));
        } catch (DateTimeParseException e) {
            throw new KobaException("Invalid date format!");
        }
    }

    private static AddCommand AddEvent(String description) throws KobaException {
        String[] splitString = description.split(" /at ", 2);
        String task = splitString[0].trim();
        String date = splitString[1].trim();
        if (splitString.length <= 1) {
            throw new KobaException("The date cannot be empty!");
        }
        return new AddCommand(new Event(task, false, date));
    }

    private static FindCommand getFindCommand(String description) {
        String[] splitString = description.split("find ", 2);
        String keyword = splitString[1];
        return new FindCommand(keyword);
    }
}