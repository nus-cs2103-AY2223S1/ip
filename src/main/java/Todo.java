public class Todo extends Task{

    public Todo(String description) {
        super(description);
        super.numberOfTasks += 1;
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
