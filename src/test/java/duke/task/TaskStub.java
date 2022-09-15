package duke.task;

public class TaskStub extends Task{
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