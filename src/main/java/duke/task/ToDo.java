package duke.task;

public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public ToDo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    @Override
    public String getSaveFormat() {
        return "T " + super.getSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
