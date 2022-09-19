package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

/**
 * Parser helper to read and parse raw text input into commands.
 */
public class Parser {

    private static String getTaskName(String[] msg) {
        assert(msg.length > 1);
        String input = "";
        for (int i = 1; i < msg.length; i++) {
            input += msg[i];
            if (i < msg.length - 1) {
                input += " ";
            }
        }
        return input;
    }

    private static LocalDateTime parseDateTime(String input) throws DukeException {
        String timeString = input.replace('/', '-');
        try {
            return LocalDateTime.parse(timeString,
                    DateTimeFormatter.ofPattern("d-M-yyyy H:m"));
        } catch (Exception e) {
            throw(new DukeException("Error parsing time. Please " +
                    "use dd-mm-yyyy hh:mm format instead."));
        }
    }

    private static String[] tokenizeInput(
            String input, String type) throws DukeException {
        String[] msg = input.split(" ");
        if (msg.length < 2) {
            throw(new DukeException("nothing to " + type + "!"));
        }
        return msg;
    }

    /**
     * Parses raw input and returns corresponding command.
     *
     * @param input Raw input string.
     * @return Command to be executed.
     * @throws Exception  If there is an error in parsing.
     */
    public static Command parse(String input) throws Exception {

        if (input.equals("bye")) {

            return new ExitCommand();

        } else if (input.equals("list")) {

            return new ListCommand();

        } else if (input.startsWith("mark")) {

            String[] msg = tokenizeInput(input, "mark");
            return new MarkCommand(Integer.valueOf(msg[1]) - 1);

        } else if (input.startsWith("unmark")) {

            String[] msg = tokenizeInput(input, "unmark");
            return new UnmarkCommand(Integer.valueOf(msg[1]) - 1);

        } else if (input.startsWith("delete")) {

            String[] msg = tokenizeInput(input, "delete");
            return new DeleteCommand(Integer.valueOf(msg[1]) - 1);

        } else if (input.startsWith("todo")) {

            String[] msg = tokenizeInput(input, "add");
            return new TodoCommand(getTaskName(msg));

        } else if (input.startsWith("deadline")) {

            String[] msg = input.split("/by ");
            if (msg.length < 2) {
                throw(new DukeException("no date specified!"));
            }

            String[] tmp = tokenizeInput(msg[0], "add");
            return new DeadlineCommand(getTaskName(tmp), parseDateTime(msg[1]));

        } else if (input.startsWith("event")) {

            String[] msg = input.split("/at ");
            if (msg.length < 2) {
                throw(new DukeException("no date specified!"));
            }

            String[] tmp = tokenizeInput(msg[0], "add");
            return new EventCommand(getTaskName(tmp), parseDateTime(msg[1]));

        } else if (input.startsWith("find")) {
            String[] msg = tokenizeInput(input, "find");
            return new FindCommand(getTaskName(msg));

        } else if (input.startsWith("sort")) {
            String[] msg = tokenizeInput(input, "sort by");
            String sortType = msg[1];

            if ("alphabetically".startsWith(sortType)) {
                sortType = "alphabetically";
            } else if ("chronologically".startsWith(sortType)) {
                sortType = "chronologically";
            } else {
                throw (new DukeException("sort type invalid! try 'alphabetically' or 'chronologically'"));
            }

            boolean isDescending = false;
            if (msg.length > 2 && "descending".startsWith(msg[2])) {
                isDescending = true;
            }

            return new SortCommand(sortType, isDescending);

        } else {
            throw(new DukeException("I do not understand!"));
        }
    }
}
