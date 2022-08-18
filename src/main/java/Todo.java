public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String color = (isDone ? ANSI_GREEN : ANSI_RED);
        return color + "[T]" + super.toString() + ANSI_RESET;
    }
}
