public class Todo extends Task{
    public Todo(String description) {
        super(description);
        taskIncrementer();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
