package duke.command;

import duke.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A command that adds a Deadline to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    private AddDeadlineCommand(String command, Deadline deadline) {
        super(command, deadline);
    }

    public static AddDeadlineCommand of(String command) throws IllegalArgumentException {
        boolean isDone = command.contains("/done");
        if (isDone) {
            command = command.replace("/done", "");
        }
        String[] c1 = command.split("/by");
        String text = c1[0].replaceFirst("deadline", "").strip();
        String time = c1.length > 1 ? c1[1].strip() : "";
        if (text.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (time.isEmpty()) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a time for the deadline.\n");
        } else {
            LocalDateTime timeObj = null;
            try {
                timeObj = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the deadline.\n");
            }
            return new AddDeadlineCommand(command, new Deadline(isDone, text, timeObj));
        }
    }
}
