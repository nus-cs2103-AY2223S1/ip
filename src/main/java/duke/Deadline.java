package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains additional information relevant to a Deadline task.
 * 
 * @author Siau Wee
 */
public class Deadline extends Task implements Comparable<Task> {

    private static final DateTimeFormatter FORMAT_DDMMYY_TIME =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            
    private final LocalDateTime deadLine;

    /**
     * Constructor to initialise a Deadline object with given
     * taskname and deadline.
     * 
     * @param taskName The name of the Deadline to be created.
     * @param deadLine The deadline date and time of the Deadline object
     */
    public Deadline(String taskName, LocalDateTime deadLine, boolean marked) {
        super(taskName, marked);
        this.deadLine = deadLine;
    }

    public LocalDateTime getDeadLine() {
        return this.deadLine;
    }

    /**
     * Returns the string representation of the current Deadline object.
     * 
     * @return String with task's name, and deadline date and time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " +
                this.deadLine.format(FORMAT_DDMMYY_TIME);
    }

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return -1;
        } else {
            if (otherTask instanceof Deadline) {
                @SuppressWarnings("unchecked")
                Deadline deadlineTask = (Deadline) otherTask;
                return this.deadLine.compareTo(deadlineTask.deadLine);
            } else if (otherTask instanceof Event) {
                @SuppressWarnings("unchecked")
                Event eventTask = (Event) otherTask;
                return this.deadLine.compareTo(eventTask.getEventTime());
            } else {
                return 0;
            }
        }
    }
}
