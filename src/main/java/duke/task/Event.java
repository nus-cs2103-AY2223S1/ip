package duke.task;

import java.time.LocalDateTime;

import duke.util.Parser;

/**
 * Represents Event Tasks
 */
public class Event extends Task {
    public static final String SYMBOL = "E";
    private LocalDateTime time;

    protected Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Return the String representation of the Task.
     * @return The String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + Parser.parseDateTimeToString(this.time) + ")";
    }

    /**
     * Returns the formatted string representation of the object.
     * @return The formatted string representation of the object.
     */
    @Override
    public String toFormattedString() {
        return Parser.combineAttributes(SYMBOL,
                Integer.toString(Parser.convertBoolToInt(this.getIsDone())),
                this.getName(),
                Parser.parseDateTimeToString(this.getTime()));
    }

    @Override
    public Event clone() {
        Event result = new Event(String.valueOf(this.getName()), this.getTime());
        if (this.getIsDone()) {
            result.markAsDone();
        }
        return result;
    }

    /**
     * Returns whether the given Task is same as this one.
     * @param task The given Task
     * @return The boolean whether the given Task is same as this one.
     */
    @Override
    public boolean isSameTask(Task task) {
        if (task instanceof Event) {
            Event that = (Event) task;
            return this.getName().equals(task.getName())
                    && this.getTime().equals(that.getTime());
        }
        return false;
    }

    /**
     * Returns boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Event) {
            Event e = (Event) obj;
            if (e == null) {
                return false;
            }
            return this.getName().equals(e.getName())
                    && this.getTime().equals(e.getTime())
                    && this.getIsDone() == e.getIsDone();
        }
        return false;
    }
}
