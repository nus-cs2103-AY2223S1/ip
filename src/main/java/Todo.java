public class Todo extends Task {
    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.isBlank()) {
            throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String getStorageString() {
        return "T | " + (this.isDone ? "1 | " : "0 | ") + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
