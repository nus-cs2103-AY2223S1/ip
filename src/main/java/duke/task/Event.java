package duke.task;

/**
 * {@code Event} is a type of {@code Task} where user must specify the description and the "/at" remark
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor for {@code Event}
     * @param description the description of the {@code Event}
     * @param at the remark of the {@code Event}
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * To display the {@code Task} and the type of the {@code Task}
     * @return the string representation of the {@code Task}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
