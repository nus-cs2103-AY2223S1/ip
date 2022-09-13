package Duke;

/**
 * This is the deadline class that is
 * being called by the user
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for class Deadline
     * @param description   Description of the deadline
     * @param by            The date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        assert description != "": "Description should not be empty";
        this.by = by;
    }
    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}