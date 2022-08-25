public class Todo extends Task {
    public Todo(String details) {
        super(details);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
}
