package duke;

import java.util.Objects;

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
     * @throws DukeException If the type does not exist in Duke.
     */
    @Override
    public void run(Duke duke) throws DukeException {
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
            throw new DukeException("");
        }
        duke.addTask(task);
    }

    /**
     * Checks equality to another Object.
     *
     * @param o Other Object.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        AddCommand that = (AddCommand) o;
        return type == that.type && Objects.equals(description, that.description) && Objects.equals(detail, that.detail);
    }
}
