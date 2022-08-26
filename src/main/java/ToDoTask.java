public class ToDoTask extends Task{

    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
