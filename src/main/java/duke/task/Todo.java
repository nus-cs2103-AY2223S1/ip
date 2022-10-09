package duke.task;

/**
 * Task with no deadline.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String type = "[T]";
        String taskDescription = super.toString();
        return type + taskDescription;
    }

}
