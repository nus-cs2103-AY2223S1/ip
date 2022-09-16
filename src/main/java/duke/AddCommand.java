package duke;

/**
 * Represents a Command to add a task to Duke, either a ToDo, a Deadline, or an Event.
 */
public class AddCommand extends Command {
    protected char type;
    protected String description;
    protected String detail;

    /**
     * Constructor of AddCommand with type and description.
     *
     * @param type Type of Task to add.
     * @param description Description of Task to add.
     */
    public AddCommand(char type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Constructor of AddCommand with type, description, and detail.
     *
     * @param type Type of Task to add.
     * @param description Description of Task to add.
     * @param detail Additional detail of Task to add (deadline for Deadline and time for Event).
     */
    public AddCommand(char type, String description, String detail) {
        this(type, description);
        this.detail = detail;
    }

    /**
     * Run the AddCommand, add a Task to Duke.
     *
     * @param duke Duke instance to run the AddCommand at.
     */
    @Override
    public void run(Duke duke) {
        Task task;
        switch (type) {
        case 'T':
            task = new ToDo(description);
            break;
        case 'D':
            task = new Deadline(description, detail);
            break;
        case 'E':
            task = new Event(description, detail);
            break;
        default:
            task = new Task(""); // error
        }
        duke.add(task);
    }
}
