package sky.task;

/**
 * The ToDo class encapsulates a ToDo.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public ToDo makeACopy() {
        ToDo copiedTask = new ToDo(super.description);
        return copiedTask;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
