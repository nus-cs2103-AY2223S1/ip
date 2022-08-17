public class Todo extends Task {

    protected String id = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return id + super.toString();
    }
}