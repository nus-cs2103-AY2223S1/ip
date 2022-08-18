public class Todo extends Task {
    protected String description;
    protected boolean isDone;

    public Todo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        String s = String.format("[T]%s", super.toString());
        return s;
    }
}
