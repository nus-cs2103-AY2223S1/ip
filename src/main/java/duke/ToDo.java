package duke;

/**
 * Event is specialised Task with a clear task description
 */
public class ToDo extends Task{

    /**
     * Class Constructor using description of task
     * @param taskDescription description of task
     */
    public ToDo(String taskDescription) {
        super(taskDescription.replace("todo ", ""));
    }

    /**
     * Class Constructor using description of task and completion status
     * @param taskDescription description of task
     * @param isCompleted completion status
     */
    public ToDo(String taskDescription, boolean isCompleted) {
        super(taskDescription, isCompleted);
    }

    /**
     * Returns the String format of Task for display in UI
     * @return String of task
     */
    @Override
    protected String returnDescription() {
        return "[T]" + super.returnDescription();
    }

    /**
     * Returns the String format of Task for saving to file
     * @return String of task
     */
    @Override
    protected String toWriteFile() {
        return "T , " + super.toWriteFile();
    }
}
