package duke.task;

public class Todo extends Task {

    /**
     * Constructor to create new Todo
     * 
     * @param description Description of the todo you want to create
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor to create new Todo with isDone
     * 
     * @param description Description of the todo you want to create
     * @param isDone      Whether the task is done or not
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Factory method to create new Todo
     * 
     * @param input Task description
     * @return new Todo
     */
    public static Todo createTodo(String input) {
        if (input.indexOf("/completed ") == -1) {
            return new Todo(input);
        } else {
            String description = input.split("/completed ")[0];
            boolean isDone = Boolean.parseBoolean(input.split("/completed ")[1]);
            return new Todo(description, isDone);
        }
    }

    @Override
    public String getFileString() {
        return "todo " + this.description + " /completed " + this.isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
