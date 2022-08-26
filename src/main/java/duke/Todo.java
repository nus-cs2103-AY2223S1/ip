package duke;

public class Todo extends Task {

    public Todo(String description, boolean isDone) throws DukeException {
        super(description, isDone);
        if (description.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Converts the todo in tasks.txt into a Todo object.
     */
    public static Todo parse(String task) {
        boolean isDone = task.substring(4, 5).equals(" ") ? false : true;
        return new Todo(task.substring(7), isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}