package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@code Deadline} is a type of {@code Task} where user must specify the description and the "/by" remark
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * Constructor for {@code Deadline}
     * @param description the description of the {@code Deadline}
     * @param date the remark of the {@code Deadline}
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * To display the {@code Task} and the type of the {@code Task}
     * @return the string representation of the {@code Task}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
