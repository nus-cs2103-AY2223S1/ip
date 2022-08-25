package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.others.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Parser {

    private static final List<String> formatStrings = Arrays.asList(
            "yyyy/MM/dd", "yyyy-MM-dd", "MMM dd yyyy");

    public static Command parse(String fullCommand) throws DukeException {

        String commandWord;
        String description;
        if (fullCommand.contains(" ")) {
            String[] arr = fullCommand.split(" ", 2);
            commandWord = arr[0];
            description = arr[1];
        } else {
            commandWord = fullCommand;
            description = null;
        }

        switch (commandWord) {
        case AddCommand.COMMAND_WORD_DEADLINE:
            // Fallthrough
        case AddCommand.COMMAND_WORD_EVENT:
            // Fallthrough
        case AddCommand.COMMAND_WORD_TODO:
            return prepareAdd(commandWord, description);
        case DeleteCommand.COMMAND_WORD:
            // Fallthrough
        case MarkCommand.COMMAND_WORD:
            // Fallthrough
        case UnmarkCommand.COMMAND_WORD:
            return parseIndex(commandWord, description);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        default:
            throw new DukeException("☹ I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Command prepareAdd(String commandWord, String description) throws DukeException {
        if (description == null) {
            throw new DukeException("☹ Description of a duke.task cannot be empty!");
        }
        if (commandWord.equals(AddCommand.COMMAND_WORD_TODO)) {
            return new AddCommand(new ToDo(description));
        } else {
            return parseDate(commandWord, description);
        }
    }

    public static Command parseDate(String commandWord, String description) throws DukeException {
        switch (commandWord) {
        case AddCommand.COMMAND_WORD_DEADLINE:
            if (description.contains(" /by ")) {
                String[] arr = description.split(" /by ", 2);
                String message = arr[0];
                LocalDate date = parseDateFormats(arr[1]);
                return new AddCommand(new Deadline(message, date));
            } else {
                throw new DukeException(
                        "☹ Please follow the format <deadline description /by date>");
            }
        default:
            if (description.contains(" /at ")) {
                String[] arr = description.split(" /at ", 2);
                String message = arr[0];
                LocalDate date = parseDateFormats(arr[1]);
                return new AddCommand(new Event(message, date));
            } else {
                throw new DukeException(
                        "☹ Please follow the format <event description /at date>");
            }
        }
    }

    public static LocalDate parseDateFormats(String dateString) throws DukeException {
        for (String formatString : formatStrings) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
                Date date = dateFormat.parse(dateString);
                SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
                String result = newFormat.format(date);
                return LocalDate.parse(result);
            } catch (ParseException pe) {
                // Let the loop continue
            }
        }
        throw new DukeException("☹ Invalid date format!");
    }

    public static Command parseIndex(String commandWord, String description) throws DukeException {
        try {
            int index = Integer.parseInt(description) - 1;
            switch (commandWord) {
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(index);
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(index);
            default:
                return new UnmarkCommand(index);
            }
        } catch (NumberFormatException nfe) {
            throw new DukeException("☹ Please enter an index of a duke.task!");
        }
    }

}
