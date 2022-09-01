public class Todo extends Task {
    /**
     * Construct Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    public Todo(SaveLine line) {
        super(line);
    }

    /**
     * Shows the todo name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType("todo");
        return ret;
    }
}
