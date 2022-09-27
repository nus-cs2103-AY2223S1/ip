package duke.task;


import java.time.LocalDateTime;

import duke.util.Parser;

/**
 * Represents Deadline tasks.
 */
public class Deadline extends Task {
    public static final String SYMBOL = "D";
    private LocalDateTime time;

    /**
     * Constructs the Deadline with given Task information and time.
     * @param name The given task information.
     * @param time The given deadline time.
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + Parser.parseDateTimeToString(this.time) + ")";
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

    /**
     * Returns an identical Deadline.
     * @return An identical Deadline.
     */
    @Override
    public Deadline clone() {
        Deadline result = new Deadline(this.getName(), this.getTime());
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
        if (task instanceof Deadline) {
            Deadline that = (Deadline) task;
            return this.getName().equals(task.getName())
                    && this.getTime().equals(that.getTime());
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            if (d == null) {
                return false;
            }
            return this.getName().equals(d.getName())
                    && this.getTime().equals(d.getTime())
                    && this.getIsDone() == d.getIsDone();
        }
        return false;
    }
}
