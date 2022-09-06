package duke;

/**
 * Deadline class which inherits from the Task class.
 */
public class FixedDurationTask extends Task {
    private final int duration;

    /**
     * Constructor for the FixedDurationTask class.
     *
     * @param description Description of the task.
     * @param duration The duration that the task takes.
     */
    public FixedDurationTask(String description, int duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " (takes: " + duration + " hours)";
    }

    @Override
    public String toLine() {
        String line = "F";
        if (this.isDone) {
            line += ("*1*" + this.item + "*" + duration);
        } else {
            line += ("*0*" + this.item + "*" + duration);
        }
        return line;
    }
}
