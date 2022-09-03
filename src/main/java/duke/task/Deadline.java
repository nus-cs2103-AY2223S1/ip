package duke.task;

import duke.exception.DukeException;
import duke.ui.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private LocalDate time;

    private static final String metaPattern = "(^.+)(\\s?+/by\\s?+)(.+)?$";
    private static String extractDescription(String meta) throws DukeException {
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException(Message.NO_BY_ERROR);
        }
        return m.group(1);
    }

    public Deadline(String meta) throws DukeException {
        super(extractDescription(meta));
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException(Message.NO_BY_ERROR);
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
        return String.format("D|%d|%s /by %s", this.isDone ? 1 : 0, this.description, this.time);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}