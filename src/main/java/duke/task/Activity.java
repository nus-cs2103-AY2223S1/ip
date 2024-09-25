package duke.task;

import java.time.Duration;

/**
 * Represents an Errand task with a fixed duration.
 *
 * @author Justin Peng
 */
public class Activity extends Task {
    /** Deadline of the task */
    private final Duration duration;

    /**
     * Creates a new undone task with the specified description and duration.
     *
     * @param description The description of the task.
     * @param duration The duration of the task.
     */
    public Activity(String description, Duration duration) {
        super(description);
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    /**
     * {@inheritDoc}
     *
     * @return 'D'
     */
    @Override
    public char getType() {
        return 'A';
    }

    @Override
    public String toString() {
        return String.format(
                "[A]%s (for: %d hours)",
                super.toString(),
                this.duration.toHoursPart()
        );
    }
}
