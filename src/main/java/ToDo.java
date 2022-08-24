public class ToDo extends Task {
    /**
     * Constructor for ToDos class
     * @param description description of task to do
     */
    public ToDo(String description, Boolean isDone) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
