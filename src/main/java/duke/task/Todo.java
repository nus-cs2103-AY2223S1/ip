package duke.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
        this.type = Type.TODO;
    }

    @Override
    public String toDataString() {
        return "T" + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
