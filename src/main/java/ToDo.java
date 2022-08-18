public class ToDo extends Task {

    protected String due;

    public ToDo (String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " (by: " + due + ")";
    }
}
