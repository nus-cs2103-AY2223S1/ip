package duke.task;

public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (isCompleted()) {
            return String.format("[T][1] %s", getTaskName());
        } else {
            return String.format("[T][0] %s", getTaskName());
        }
    }
}