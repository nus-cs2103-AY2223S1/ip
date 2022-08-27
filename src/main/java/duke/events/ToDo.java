package duke.events;

/**
 * <p>Class ToDos is a concrete class that extends the Task class.</p>
 * <p>This class is used to create a task that is a to-do.</p>
 * <p>This class is a concrete class because it has an implementation.</p>
 */
public class ToDo extends Task {

    private String taskName;

    /**
     * Constructor for ToDo.
     * @param taskName the name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
        this.taskName = taskName;
    }

    /**
     * Method that returns a String showing the completion status of the task.
     * @return a String showing the completion status of the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Method that returns the completion status of the task in a special format for creating a save file.
     * @return a String showing the completion status of the task.
     */
    @Override
    public String getSaveData() {
        return "T" + " | " + super.isDone() + " | " + taskName;
    }
}
