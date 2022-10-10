package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.excecption.InvalidInputException;
import duke.task.TaskList;

/**
 * Class for parsing user input.
 */
public class TaskParser {

    /**
     * Parses the input and returns the corresponding command.
     *
     * @param line The input to be parsed.
     * @param todolist The TaskList to be modified.
     * @return The corresponding command.
     * @throws InvalidInputException If the input is invalid.
     */
    public static String parse(String line, TaskList todolist) {
        if (line.matches("deadline .* by .*")) {
            assert line.startsWith("deadline ");
            String[] res = line.substring(9).split(" by ");
            String desc = res[0];
            String deadline = res[1];

            todolist.addDeadline(desc, deadline);
            return String.format("Added: %s (by: %s)", desc, deadline);
        }

        if (line.matches("deadline .* /by .*")) {
            assert line.startsWith("deadline ");
            String[] res = line.substring(9).split(" /by ");
            String desc = res[0];
            LocalDate date = LocalDate.parse(res[1]);

            todolist.addDeadline(desc, date);
            return String.format(
                    "Added: %s (by: %s)", desc, date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            );
        }

        if (line.matches("event .* at .*")) {
            assert line.startsWith("event ");
            String[] res = line.substring(6).split(" at ");
            String desc = res[0];
            String time = res[1];

            todolist.addEvent(desc, time);
            return String.format("Added: %s (at: %s)", desc, time);
        }

        if (line.matches("todo .*")) {
            assert line.startsWith("todo ");
            line = line.substring(5);
            todolist.addTask(line);
            return "Added: " + line;
        }

        throw new InvalidInputException();

    }
}
