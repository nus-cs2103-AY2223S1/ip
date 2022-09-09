package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.task.Event;

/**
 * A command that adds an Event to the task list.
 */
public class AddEventCommand extends AddCommand {
    private AddEventCommand(String command, Event event) {
        super(command, event);
        assert(command.startsWith("event"));
    }

    /**
     * Factory method taking in input string from user.
     * Throws IllegalArgumentException if the event's description is not given,
     * or a time is not given after "/at", or the time given is in the incorrect format.
     *
     * @param command input string from user, prefixed with "event".
     * @return AddEventCommand instance that adds an event to the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static AddEventCommand of(String command) throws IllegalArgumentException {
        assert(command.startsWith("event"));

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

        String[] commandTimeArr = commandWithoutTag.split("/at");
        String text = commandTimeArr[0].replaceFirst("event", "").strip();
        String time = commandTimeArr.length > 1 ? commandTimeArr[1].strip() : "";
        if (text.isEmpty()) {
            throw new IllegalArgumentException(":( OOPS!!! The description of an event cannot be empty.\n");
        } else if (time.isEmpty()) {
            throw new IllegalArgumentException(":( OOPS!!! Provide a time for the event.\n");
        }

        LocalDateTime timeObj;
        try {
            timeObj = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("🙁 OOPS!!! Provide a valid time (dd/MM/yy HHmm) for the event.\n");
        }
        return new AddEventCommand(command, new Event(isDone, text, tag, timeObj));
    }
}
