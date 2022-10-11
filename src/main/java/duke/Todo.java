package duke;

/**
 * Todo class that extends from Task
 * @author amresh A0235398R
 */
public class Todo extends Task {

    /**
     * Constructor for Todo object
     *
     * @param description Description of Todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return super.equals(todo);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
