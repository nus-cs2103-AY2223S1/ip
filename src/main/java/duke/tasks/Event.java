package duke.tasks;

/**
 * Class for Event Task.
 * @author lauralee
 */
public class Event extends Task {

    private String time;

    /**
     * Constructor for Event class.
     * @param name Name of the Event being added.
     * @param time Time of the Event being added.
     */
    public Event(String name, String time) {
        super(name, "E");
        this.time = time;
    }

    @Override
    public String output() {
        return super.output() + " (at: " + this.time + ")";
    }
}
