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
     * Factory method to create new Todo
     * 
     * @param input Task description
     * @return new Todo
     */
    public static Todo createTodo(String input) {
        return new Todo(input);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
