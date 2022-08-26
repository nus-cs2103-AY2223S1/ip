package dukeprogram;

import java.io.Serializable;

public class Task implements Serializable {
    public enum TaskState {
        Done,
        NotDone
    }
    private final String name;
    private TaskState taskState;

    public Task(String name) {
        this.name = name;
        this.taskState = TaskState.NotDone;
    }

    public Task(String name, boolean done) {
        this(name);
        this.taskState = TaskState.Done;
    }

    public String getName() {
        return name;
    }

    public void MarkJobState(boolean isComplete) {
        this.taskState = isComplete ? TaskState.Done : TaskState.NotDone;
    }

    @Override
    public String toString() {
       return String.format("[%s] ", taskState.equals(TaskState.Done) ? "X" : " ") + name;
    }
}
