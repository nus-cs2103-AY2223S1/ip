public class Todo extends Task {
    /**
     * Constructor for Todo
     * @param title The title of Todo
     */
    public Todo(String title) {
        super(title);
    };

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
