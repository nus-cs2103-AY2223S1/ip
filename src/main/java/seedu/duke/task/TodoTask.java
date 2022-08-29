package seedu.duke.task;

public class TodoTask extends Task {
    public TodoTask(String taskname) {
        super(taskname);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String saveFileFormat() {
        return "T###" + super.saveFileFormat();
    }
}
