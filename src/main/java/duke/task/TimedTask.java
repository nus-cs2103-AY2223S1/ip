package duke.task;

import java.time.LocalDate;

/**
 * TimedTask is an abstract class with localdate
 */
public abstract class TimedTask extends Task implements Comparable<Task> {
    protected String by;
    protected LocalDate localDate;
    /**
     * @param description
     */
    public TimedTask(String description, String by) {
        super(description);
        this.by = by;
        this.localDate = LocalDate.parse(by);
    }

    @Override
    public int compareTo(Task o) {
        boolean sameType = (this instanceof Event && o instanceof Event)
                || (this instanceof Deadline && o instanceof Deadline);
        if (sameType) {
            return this.localDate.compareTo(((TimedTask) o).localDate);
        } else if (this instanceof Event) { // there is both event and deadline
            return -1;
        } else { // o is event
            return 1;
        }
    }

}
