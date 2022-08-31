package duke.tasks;

import duke.support.DatesAndTimes;

/**
 * Class for the Deadline Task.
 * @author lauralee
 */
public class Deadline extends Task {
    private DatesAndTimes time;

    /**
     * Constructor for the Deadline class.
     * @param name Name of the Deadline task being added.
     * @param time Time at which the Deadline task needs to be completed by.
     */
    public Deadline(String name, String time) {
        super(name, "D");
        this.time = new DatesAndTimes(time);
    }

    @Override
    public String output() {
        return super.output() + " (by: " + this.time.output() + ")";
    }
}
