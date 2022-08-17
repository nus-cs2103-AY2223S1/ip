public class Todo extends Task{
    public Todo(String description) {
        super(description);
        addNewTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
