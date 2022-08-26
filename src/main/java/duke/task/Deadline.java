package duke.task;


import java.time.LocalDateTime;

import duke.Parser;

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

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + Parser.parseDateTimeToString(this.time) + ")";
    }

    @Override
    public String toFormattedString() {
        return Parser.combineAttributes(SYMBOL,
                Integer.toString(Parser.convertBoolToInt(this.getIsDone())),
                this.getName(),
                Parser.parseDateTimeToString(this.getTime()));
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
