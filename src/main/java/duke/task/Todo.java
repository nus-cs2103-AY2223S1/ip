package duke.task;

/**
 * {@code Todo} is a type of {@code Task} where user must specify the description
 */
public class Todo extends Task {

    /**
     * Constructor for {@code Todo}
     * @param description the description of the {@code Todo}
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * To display the {@code Task} and the type of the {@code Task}
     * @return the string representation of the {@code Task}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
