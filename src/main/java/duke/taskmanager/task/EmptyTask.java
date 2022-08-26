package duke.taskmanager.task;

public class EmptyTask extends Task {
    public EmptyTask() {
        super("");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String getFormattedString() {
        return "";
    }
}
