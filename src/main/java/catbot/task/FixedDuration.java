package catbot.task;

import java.time.Duration;

/**
 * A fixed duration task which contains a duration required to complete the task, which inherits from Task.
 */
public class FixedDuration extends Task {

    /** Stores the fixed duration needed to complete the task. */
    private Duration duration;

    /**
     * Constructor for a FixedDuration.
     *
     * @param description The description of the FixedDuration.
     * @param hours       The number of hours needed to complete the task.
     * @param minutes     The number of minutes needed to complete the task.
     */
    public FixedDuration(String description, int hours, int minutes) {
        super(description);
        this.duration = Duration.ofHours(hours);
        this.duration = this.duration.plusMinutes(minutes);
    }

    /**
     * Returns the string representation of the FixedDuration object to be stored in the file.
     *
     * @return The string representation of the FixedDuration object to be stored in the file.
     */
    @Override
    public String toFile() {
        return "F | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.duration.toHours()
                + " | " + this.duration.toMinutesPart() + "\n";
    }

    /**
     * Returns the string representation of the FixedDuration object.
     *
     * @return The string representation of the FixedDuration object.
     */
    @Override
    public String toString() {
        String fixedDuration = "";
        if (this.duration.toHours() > 0) {
            fixedDuration += this.duration.toHours() + " hours";
            if (this.duration.toMinutesPart() > 0) {
                fixedDuration += ", " + this.duration.toMinutesPart() + " minutes";
            }
        } else {
            assert this.duration.toMinutesPart() > 0 : "hour and minutes are both non positive";
            fixedDuration += this.duration.toMinutesPart() + " minutes";
        }
        return "[F]" + super.toString() + " (needs: " + fixedDuration + ")";
    }
}
