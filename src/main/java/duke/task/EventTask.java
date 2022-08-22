package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private final LocalDateTime time;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

    public EventTask(String[] args) {
        super(args[0], 'E');

        try {
            this.time = LocalDateTime.parse(args[1], inputFormatter);
        }  catch (DateTimeParseException e) {
            throw new DukeException("The Date/Time was not understood");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.time.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + this.time.format(inputFormatter);
    }
}
