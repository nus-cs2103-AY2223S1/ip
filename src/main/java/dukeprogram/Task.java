package dukeprogram;

import java.io.Serializable;

/**
 * A serializable Task class that describes a task in a tasklist
 */
public class Task implements Serializable {
    public enum TaskState {
        Done,
        NotDone
    }
    private final String name;
    private TaskState taskState;

    /**
     * Creates a new task with the given name
     * @param name task name
     */
    public Task(String name) {
        this.name = name;
        this.taskState = TaskState.NotDone;
    }

    /**
     * Creates a task with the given name and completion status
     * @param name the task's name
     * @param isDone the completion status describing whether the task is done or not
     */
    public Task(String name, boolean isDone) {
        this(name);
        this.taskState = TaskState.Done;
    }

    /**
     * Returns the name of this task
     * @return the name of this task
     */
    public String getName() {
        return name;
    }

    /**
     * Annotates this task as either complete or incomplete
     * @param isComplete the state to annotate this task with
     */
    public void MarkJobState(boolean isComplete) {
        this.taskState = isComplete ? TaskState.Done : TaskState.NotDone;
    }

    /**
     * Returns a string describing the state of completion of this task followed
     * by the name of the task
     * @return a string formatted as "[state] task_name"
     */
    @Override
    public String toString() {
       return String.format("[%s] ", taskState.equals(TaskState.Done) ? "X" : " ") + name;
    }
}
