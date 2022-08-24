public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Format of parsing:
     * Type of task # status of task # description # timing
     * @return String that is in the parsing format.
     */
    @Override
    public String parse() {
        return String.format("T # %s # %s # ", super.getStatusIcon(), super.getTaskDescription());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
