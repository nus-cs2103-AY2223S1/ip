package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

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

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (at: " + Parser.parseDateTimeToString(this.time) + ")";
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
        if (obj instanceof Event) {
            Event e = (Event) obj;
            if (e == null) {
                return false;
            }
            if (this.time == e.time && this.getName() == e.getName()) {
                return true;
            }
            if (this.getName() == null || this.time == null) {
                return false;
            }
            if (e.getName() == null || e.getTime() == null) {
                return false;
            }
            return this.getName().equals(e.getName())
                    && this.getIsDone() == e.getIsDone()
                    && this.getTime().equals(e.getTime());
        }
        return false;
    }
}
