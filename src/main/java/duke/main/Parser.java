package duke.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.FindNearestTimeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.errors.DukeException;

/**
 * Reads user input
 */
public class Parser {
    /**
     * Parses user input and creates the necessary commands
     * @param userReply String from the user
     * @return Commands for Duke to execute
     * @throws DukeException exception thrown in validation methods
     */
    public static Command parse(String userReply) throws DukeException {
        String[] splitReply = userReply.split(" ");
        if (isExitCommand(userReply)) {
            return new ExitCommand();
        } else if (isFindCommand(splitReply)) {
            String keyword = validateFind(splitReply);
            return new FindCommand(keyword);
        } else if (isListCommand(userReply)) {
            return new ListCommand();
        } else if (isMarkCommand(splitReply)) {
            int index = validateIndex(userReply);
            return new MarkCommand(index);
        } else if (isUnmarkCommand(splitReply)) {
            int index = validateIndex(userReply);
            return new UnmarkCommand(index);
        } else if (isTodoCommand(splitReply)) {
            String description = validateTodo(splitReply);
            return new AddTodoCommand(description);
        } else if (isDeadlineCommand(splitReply)) {
            int by = validateBy(splitReply);
            String description = validateDescription(splitReply, by);
            LocalDateTime dateTime = validateDateTime(splitReply, by);
            return new AddDeadlineCommand(description, dateTime);
        } else if (isEventCommand(splitReply)) {
            int by = validateAt(splitReply);
            String description = validateDescription(splitReply, by);
            LocalDateTime duration = validateDateTime(splitReply, by);
            return new AddEventCommand(description, duration);
        } else if (isDeleteCommand(splitReply)) {
            int index = validateIndex(userReply);
            return new DeleteCommand(index);
        } else if (isNextAvailableTiming(splitReply)){
            long index = validateIndex(userReply);
            return new FindNearestTimeCommand(index);
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    private static boolean isExitCommand(String userReply) {
        return userReply.equals("bye");
    }

    private static boolean isListCommand(String userReply) {
        return userReply.equals("list");
    }

    private static boolean isMarkCommand(String[] splitReply) {
        return splitReply[0].equals("mark");
    }

    private static boolean isUnmarkCommand(String[] splitReply) {
        return splitReply[0].equals("unmark");
    }

    private static boolean isTodoCommand(String[] splitReply) {
        return splitReply[0].equals("todo");
    }

    private static boolean isDeadlineCommand(String[] splitReply) {
        return splitReply[0].equals("deadline");
    }

    private static boolean isEventCommand(String[] splitReply) {
        return splitReply[0].equals("event");
    }

    private static boolean isDeleteCommand(String[] splitReply) {
        return splitReply[0].equals("delete");
    }

    private static boolean isFindCommand(String[] splitReply) {
        return splitReply[0].equals("find");
    }

    private static boolean isNextAvailableTiming(String[] splitReply) {
        return splitReply[0].equals("next");
    }

    private static int validateIndex(String userReply) throws DukeException {
        try {
            if (userReply.split(" ").length != 2) {
                throw new DukeException("Issues with indexing");
            }
            int index = Integer.parseInt(userReply.replaceAll("[^0-9]", ""));
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number keyed in!");
        }
    }

    private static String validateTodo(String[] splitReply) throws DukeException {
        if (splitReply.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String[] descriptionArray = Arrays.copyOfRange(splitReply, 1, splitReply.length);
        String description = String.join(" ", descriptionArray);
        return description;
    }

    private static int validateBy(String[] splitReply) throws DukeException {
        int by = -1;
        for (int i = 0; i < splitReply.length; i++) {
            if (splitReply[i].equals("/by")) {
                by = i;
                break;
            }
        }
        if (by == -1) {
            throw new DukeException("invalid duration");
        }
        return by;
    }

    private static int validateAt(String[] splitReply) throws DukeException {
        int by = -1;
        for (int i = 0; i < splitReply.length; i++) {
            if (splitReply[i].equals("/at")) {
                by = i;
                break;
            }
        }
        if (by == -1) {
            throw new DukeException("invalid duration");
        }
        return by;
    }

    private static String validateDescription(String[] splitReply, int by) {
        String[] descriptionArray = Arrays.copyOfRange(splitReply, 1, by);
        String description = String.join(" ", descriptionArray);
        return description;
    }

    private static String validateDuration(String[] splitReply, int by) {
        String[] durationArray = Arrays.copyOfRange(splitReply, by + 1, splitReply.length);
        String duration = String.join(" ", durationArray);
        return duration;
    }

    private static LocalDateTime validateDateTime(String[] splitReply, int by) throws DukeException {
        try {
            String[] durationArray = Arrays.copyOfRange(splitReply, by + 1, splitReply.length);
            String duration = String.join(" ", durationArray);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
            return LocalDateTime.parse(duration, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("error in validate date time");
        }
    }

    private static String validateFind(String[] splitReply) throws DukeException {
        if (splitReply.length != 2) {
            throw new DukeException("OOPS!!! Only one keyword allowed for find");
        }
        String[] keywordArray = Arrays.copyOfRange(splitReply, 1, splitReply.length);
        String keyword = String.join(" ", keywordArray);
        return keyword;
    }
}
