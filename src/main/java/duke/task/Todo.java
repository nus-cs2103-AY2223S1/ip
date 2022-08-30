package duke.task;

public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param taskName the name of the todo task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns a reformatted string of the task to be stored in the text file.
     *
     * @return Reformatted string representation of the task
     */
    @Override
    public String formatTask() {
        return String.format("T | %s | %s\n", (this.isDone ? "1" : "0"), this.taskName);
    }

    /**
     * Returns a string of the task (eg.: [T][ ] read book).
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
