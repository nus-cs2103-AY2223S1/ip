public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
