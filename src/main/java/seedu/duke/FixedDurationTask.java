package seedu.duke;

public class FixedDurationTask extends Task {
    protected String duration;

    /**
     *
     * @param description
     * @param duration
     */
    public FixedDurationTask(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + "(needs " + duration + ")";
    }

    @Override
    public String toStore() {
        return "F" + super.toStore() + " : " + duration;
    }
}
