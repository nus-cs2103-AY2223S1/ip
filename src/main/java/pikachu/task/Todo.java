package pikachu.task;
public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description,isDone);
    }

    public String getName() {
        return "T";
    }

    public String getTiming() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
