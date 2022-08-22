package duke.task;

import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

    public DeadlineTask(String[] args) {
        super(args[0], 'D');

        try {
            System.out.println(args[1]);
            this.deadline = LocalDateTime.parse(args[1], inputFormatter);
        }  catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new DukeException("The Date/Time was not understood");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + this.deadline.format(inputFormatter);
    }
}
