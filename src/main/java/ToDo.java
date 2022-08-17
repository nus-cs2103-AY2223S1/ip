// This class inherits from the abstract Task class
// and encapsulates the logic of a ToDo task.
public class ToDo extends Task{

    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
