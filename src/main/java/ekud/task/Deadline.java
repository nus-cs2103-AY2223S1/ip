package ekud.task;

import ekud.exception.EkudException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDate dueDate;

    /**
     * Constructor that instantiates new instance of Deadline.
     * 
     * @param description Description of task.
     * @param dueDateString Due Date of task in YYYY-MM-DD format.
     * @throws EkudException Exception in creation of deadline due to invalid due
     *         date.
     */
    public Deadline(String description, String dueDateString) throws EkudException {
        super(description);
        this.dueDate =

                this.getDateFromString(dueDateString);
    }

    /**
     * Parses string into LocalDate object.
     * 
     * @param dateString String in YYYY-MM-DD format.
     * @return LocalDate object representing date.
     * @throws EkudException Error that occurred, due to invalid string format.
     */
    private LocalDate getDateFromString(String dateString) throws EkudException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            LocalDate date = LocalDate.parse(dateString, formatter);
            return date;
        } catch (DateTimeParseException exception) {
            throw new EkudException("Invalid date format. Please input date in format yyyy-mm-dd");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileFormat() {
        int isDone = this.getStatusIcon() == "X" ? 1 : 0;
        return String.format("D|%d|%s|%s", isDone, this.getDescription(), this.dueDate.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));
    }
}
