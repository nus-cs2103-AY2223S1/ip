/**
 * Encapsulate Todo which is-a Task
 *
 * @author: Jonas Png
 */
public class ToDo extends Task{

    /**
     * Class constructor for Todo
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
