public class Todo extends Task {

    /**
     * The constructor, does not have a "by" time
     * @param description the description of the task to do
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * The string representation of the to-do task
     * @return a string representing the description of the to-do
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
