public class ToDo extends Task{
    
    public ToDo(String description) {
        super("todo", description);
    }

    public ToDo(String description, boolean isDone) {
        super("todo", description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
