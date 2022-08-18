package duke;

public class ToDo extends Task{

    ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    String getBy() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }
}
