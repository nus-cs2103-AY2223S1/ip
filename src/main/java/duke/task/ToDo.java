package duke.task;

/**
 * Class for a ToDo task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo with specified description.
     *
     * @param description The description of the toDo to be created.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a ToDo with specified description and completeness.
     *
     * @param description The description of the ToDo to be created.
     * @param isDone If the ToDo is completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the ToDo in a string format to be saved in a local file.
     *
     * @return A string corresponding to the ToDo.
     */
    @Override
    public String stringify() {
        return String.format("%s | %s", "T", super.stringify());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof ToDo) {
            ToDo toDo = (ToDo) obj;
            return super.equals(toDo);
        } else {
            return false;
        }
    }
}
