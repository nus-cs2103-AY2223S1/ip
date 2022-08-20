public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    @Override
    public String toFileFormatString() {
        return "T" + super.toFileFormatString() + description;
    }
}