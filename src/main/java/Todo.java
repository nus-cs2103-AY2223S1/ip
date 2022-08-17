public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns String representation of the to-do task.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
