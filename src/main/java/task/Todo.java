package task;

/**
 * Represents a todo task
 */
public class Todo extends Task {
    /**
     * Todo constructor with the specified description.
     *
     * @param description a {@link String} indicating the todo description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Todo constructor with the specified description and isDone.
     *
     * @param isDone a {@link String} indicating if the todo has been marked as done
     * @param description a {@link String} indicating the todo description
     */
    public Todo(String isDone, String description) {
        super(isDone, description);;
    }

    /**
     * Returns a {@link String} representation of a todo
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a {@link String} representation of a todo
     *
     * @return {@link String}
     */
    @Override
    public String toTxt() {
        return String.format("T @@ %s", super.toTxt());
    }
}
