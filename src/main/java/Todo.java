public class Todo extends Task{
    /**
     * A constructor to intialize the Todo object with the description
     *
     * @param description The task
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + super.toString();
    }
}
