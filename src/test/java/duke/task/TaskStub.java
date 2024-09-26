package duke.task;

/**
 * A Task Stub to help with TaskList testing
 */
public class TaskStub extends Task {
    public TaskStub() {
        super("");
    }

    @Override
    public String toString() {
        return "test task";
    }

    @Override
    public String toSaveFileString() {
        return "saveFileString";
    }
}
