package duke.task;


import java.time.LocalDateTime;

import duke.util.Parser;

/**
 * Represents Deadline tasks.
 */
public class Deadline extends Task {
    public static final String SYMBOL = "D";
    private LocalDateTime time;

    protected Deadline(String name, LocalDateTime time) {
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
        if (obj instanceof Deadline) {
            Deadline d = (Deadline) obj;
            if (d == null) {
                return false;
            }
            if (this.time == d.time && this.getName() == d.getName()) {
                return true;
            }
            if (this.getName() == null || this.time == null) {
                return false;
            }
            if (d.getName() == null || d.getTime() == null) {
                return false;
            }
            return this.getName().equals(d.getName())
                    && this.getIsDone() == d.getIsDone()
                    && this.getTime().equals(d.getTime());
        }
        return false;
    }
}
