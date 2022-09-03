package duke.task;

import duke.exception.DukeException;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private LocalDate time;

    private static final String metaPattern = "(^.+)(\\s?+/at\\s?+)(.+)?$";
    private static String extractDescription(String meta) throws DukeException {
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException(Message.NO_AT_ERROR);
        }
        return m.group(1);
    }

    public Event(String meta) throws DukeException {
        super(extractDescription(meta));
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException(Message.NO_AT_ERROR);
        }
        String time = m.group(3);
        if (time == null) {
            throw new DukeException(Message.NO_TIME_ERROR);
        }
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException(Message.DATE_FORMAT_ERROR);
        }
    }

    @Override
    public String saveText() {
        return String.format("E|%d|%s /at %s", this.isDone ? 1 : 0, this.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}