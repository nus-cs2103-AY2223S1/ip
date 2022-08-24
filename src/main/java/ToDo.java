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
    public String saveData() {
        String isDone;
        if (super.isDone) {
            isDone = "O";
        } else {
            isDone = "X";
        }
        return String.format("T | %s | %s\n", isDone, super.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
