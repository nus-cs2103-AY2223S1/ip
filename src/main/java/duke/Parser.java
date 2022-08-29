package duke;

import duke.command.Command;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.DeadlineCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.TodoCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser helper to read and parse raw text input into commands.
 */
public class Parser {

    private static String getTaskName(String[] msg) {
        String input = "";
        for (int i = 1; i < msg.length; i++) {
            input += msg[i];
            if (i < msg.length - 1) input += " ";
        }
        return input;
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

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to mark!"));
            return new MarkCommand(Integer.valueOf(msg[1]) - 1);

        } else if (input.startsWith("unmark")) {

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to unmark!"));
            return new UnmarkCommand(Integer.valueOf(msg[1]) - 1);

        } else if (input.startsWith("delete")) {

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to delete!"));
            return new DeleteCommand(Integer.valueOf(msg[1]) - 1);

        } else if (input.startsWith("todo")) {

            String[] msg = input.split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to add!"));
            return new TodoCommand(getTaskName(msg));

        } else if (input.startsWith("deadline")) {

            String[] msg = input.split("/by ");
            if (msg.length < 2) throw(new DukeException("no date specified!"));

            String[] tmp = msg[0].split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to add!"));


            String timeString = msg[1].replace('/', '-');
            LocalDateTime time = LocalDateTime.parse(
                    timeString, DateTimeFormatter.ofPattern("d-M-yyyy H:m"));

            return new DeadlineCommand(getTaskName(tmp), time);

        } else if (input.startsWith("event")) {

            String[] msg = input.split("/at ");
            if (msg.length < 2) throw(new DukeException("no date specified!"));

            String[] tmp = msg[0].split(" ");
            if (msg.length < 2) throw(new DukeException("nothing to add!"));


            String timeString = msg[1].replace('/', '-');
            LocalDateTime time = LocalDateTime.parse(
                    timeString, DateTimeFormatter.ofPattern("d-M-yyyy H:m"));

            return new EventCommand(getTaskName(tmp), time);

        } else {
            throw(new DukeException("I do not understand!"));
        }
    }
}
