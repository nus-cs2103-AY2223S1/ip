package duke.task;

/**
 * Class which manages tasks of type todo.
 */
public class Todo extends Task {

    /**
     * Creates an instance of todo task.
     * @param desc description of task
     */
    public Todo(String desc) {
        super(desc);
        super.isDone = false;
        super.type = "T";
    }
}
