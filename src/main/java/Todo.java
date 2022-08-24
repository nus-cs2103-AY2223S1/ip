public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param title The title of Todo
     * @param isDone The isDone status of the Task
     */
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String fileFormat() {
        return String.format("T|%d|%s", this.isDone ? 1 : 0, this.title);
    }
}
