package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime time;

    public Event(String taskName, String time) throws DukeException {
        super(taskName);
        try {
            this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException dateTimeParseException){
            throw new DukeException("☹ OOPS!!! Cannot parse date. Enter date according to example, 02-12-2019 1800");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + "|" + (this.getTaskStatus() ? "1" : "0") + "|" + this.getTaskName() + "|"
                + this.time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
