package duke;

/**
 * Contains the information of the basic Task, Todo.
 */
public class Todo extends Task implements Comparable<Task> {

    /**
     * Constructor to initialise a Todo with the given task name
     * 
     * @param taskName The name of the Todo task
     */
    public Todo(String taskName, boolean marked) {
        super(taskName, marked);
    }

    /**
     * Returns the string representation of the Todo object.
     * 
     * @return Todo task's name
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return 0;
        } else {
            return 1;
        }
    }

}
