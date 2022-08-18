public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}