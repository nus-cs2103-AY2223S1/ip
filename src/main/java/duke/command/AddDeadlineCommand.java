package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.task.Deadline;

/**
 * A command that adds a Deadline to the task list.
 */
public class AddDeadlineCommand extends AddCommand {
    private AddDeadlineCommand(String command, Deadline deadline) {
        super(command, deadline);
        assert(command.startsWith("deadline"));
    }

    /**
     * Factory method taking in input string from user.
     * Throws IllegalArgumentException if the deadline's description is not given,
     * or a time is not given after "/by", or the time given is in the incorrect format.
     *
     * @param command input string from user, prefixed with "deadline".
     * @return AddDeadlineCommand instance that adds a deadline to the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static AddDeadlineCommand of(String command) throws IllegalArgumentException {
        assert(command.startsWith("deadline"));

        String tag;
        String commandWithoutTag;
        String[] commandTagArr = command.split("#");
        if (commandTagArr.length == 2) {
            tag = commandTagArr[1];
            commandWithoutTag = commandTagArr[0];
        } else if (commandTagArr.length > 2) {
            throw new IllegalArgumentException(":( OOPS!!! Only 1 tag can be provided.\n");
        } else {
            tag = "";
            commandWithoutTag = command;
        }

        boolean isDone = commandWithoutTag.contains("/done");
        if (isDone) {
            commandWithoutTag = commandWithoutTag.replace("/done", "");
        }

        String[] commandDeadlineArr = commandWithoutTag.split("/by");
        String text = commandDeadlineArr[0].replaceFirst("deadline", "").strip();
        String time = commandDeadlineArr.length > 1 ? commandDeadlineArr[1].strip() : "";
        if (text.isEmpty()) {
            throw new IllegalArgumentException(":( OOPS!!! The description of a deadline cannot be empty.\n");
        } else if (time.isEmpty()) {
            throw new IllegalArgumentException(":( OOPS!!! Provide a time for the deadline.\n");
        }

        LocalDateTime timeObj;
        try {
            timeObj = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the deadline.\n");
        }
        return new AddDeadlineCommand(command, new Deadline(isDone, text, tag, timeObj));
    }
}
