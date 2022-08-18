package duke.task;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String getBy() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
