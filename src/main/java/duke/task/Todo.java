package duke.task;

/**
 * The Todo class represents a general Task to be done.
 *
 * @author Edric Yeo
 */
public class Todo extends Task {

    /**
     * Constructor for a Todo instance.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataEntry() {
        int mark = this.isDone ? 1 : 0;
        return String.format("T # %d # %s\n", mark, this.description);
    }

}
