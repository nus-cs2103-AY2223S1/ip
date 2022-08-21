package duke.task;

public class ToDo extends Task{
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String saveStringFormat() {
        return String.format("T | %s", super.saveStringFormat());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
