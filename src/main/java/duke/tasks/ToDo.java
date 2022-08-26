package duke.tasks;

public class ToDo extends Task {

    /**
     * Class constructor specifying description of to do task.
     *
     * @param description the description of to do task
     */
    public ToDo(String description) {
        super(description);
    }


    /**
     * Returns string representation of to do task.
     *
     * @return string representation of to do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
