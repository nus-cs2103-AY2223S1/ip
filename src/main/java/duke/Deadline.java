package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task with a deadline.
 * @author Tan Wen Cong
 */
public class Deadline extends Task {
    private String date;
    private LocalDate localDate;
    private String formattedDate;


    /**
     * @param description Description of the Deadline
     * @param date        Date of Deadline
     * @param isDone      boolean indicating if the Deadline is done
     */
    public Deadline(String description, String date, boolean isDone) {
        super(description, "D", isDone);
        this.date = date;
        localDate = LocalDate.parse(date);
        formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns String representation of the Deadline object
     *
     * @return String representation of the Deadline object
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.formattedDate);
    }

    /**
     * Returns String representation of Deadline to be saved in Txt file
     *
     * @return String representation of Deadline to be saved in Txt file
     */
    @Override
    public String getTxtString() {
        return String.format("deadline %s /by %s", super.getTxtString(), date);
    }
}
