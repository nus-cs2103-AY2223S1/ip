package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains additional information relevant to a Deadline task.
 * 
 * @author Siau Wee
 */
public class Deadline extends Task {

    private static DateTimeFormatter FORMAT_TIME_YYYYMMDD =
            DateTimeFormatter.ofPattern("HH:mm 'on' yyyy/MM/dd");
            
    private final LocalDateTime deadLine;

    /**
     * Constructor to initialise a Deadline object with given
     * taskname and deadline.
     * 
     * @param taskName The name of the Deadline to be created.
     * @param deadLine The deadline date and time of the Deadline object
     */
    public Deadline(String taskName, LocalDateTime deadLine) {
        super(taskName);
        this.deadLine = deadLine;
    }

    /**
     * Returns the string representation of the current Deadline object.
     * 
     * @return String with task's name, and deadline date and time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                this.deadLine.format(FORMAT_TIME_YYYYMMDD) + ")";
    }
}
