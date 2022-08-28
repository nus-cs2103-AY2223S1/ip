package duke;

/**
 * Task is a representation of a job to be done
 */
public class Task {
    private String taskDescription;
    private boolean isCompleted = false;

    /**
     * Class Constructor for a Task using description of the task
     * @param taskDescription description of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription.strip();
    }

    /**
     * Class Constructor for the Task using description of task and completion status
     * @param taskDescription description of the task
     * @param isCompleted completion status of the task
     */
    public Task(String taskDescription, boolean isCompleted) {
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the String format of Task for display in UI
     * @return String of task
     */
    protected String returnDescription(){
        String cross = " ";
        if (isCompleted) {
            cross = "X";
        }
        return "[" + cross + "] " + this.taskDescription;
    }

    /**
     * Returns the String format of Task for saving to a file
     * @return String of task
     */
    protected String toWriteFile() {
        String cross = "false";
        if (isCompleted) {
            cross = "true";
        }
        return cross + " , " + this.taskDescription;
    }

    /**
     * Marks task as completed by modifying boolean
     */
    protected void markTask() {
        this.isCompleted = true;
    }

    /**
     * Marks task as incomplet by modifying boolean
     */
    protected void unmarkTask() {
        this.isCompleted = false;
    }
}
