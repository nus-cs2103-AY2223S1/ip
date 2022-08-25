package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String isDone, String description) {
        super(isDone, description);;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTxt() {
        return String.format("T @@ %s", super.toTxt());
    }
}