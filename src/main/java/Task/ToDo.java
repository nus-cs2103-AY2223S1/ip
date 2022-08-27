package Task;

public class ToDo extends Task {

    /**
     * Initialises a ToDo task.
     *
     * @param input description of the ToDo.
     */
    public ToDo(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getDone() ? "X" : " ", this.getTask());
    }
}
