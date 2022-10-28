package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a Task that has to be done by a given deadline.
 */
class Deadline extends Task {
    public static final String DELIMITER = " /by ";
    private final char tag = 'D';
    private String time;
    private LocalDate date;

    /**
     * Constructs a Deadline instance.
     *
     * @param description description of the Task accompanied by the
     *                    deadline in the format of 'yyyy-mm-dd'.
     */
    public Deadline(String description) {
        super(description.split(Deadline.DELIMITER)[0].substring(9));
        this.time = description.split(Deadline.DELIMITER)[1];
        this.date = LocalDate.parse(this.time);
    }

    /**
     * Returns the record of the Deadline's description, completion status and date to be completed.
     *
     * @return the record of the Deadline.
     */
    @Override
    public String printTask() {
        return String.format("[%s]%s (by: %s)",
                this.tag,
                super.printTask(),
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
