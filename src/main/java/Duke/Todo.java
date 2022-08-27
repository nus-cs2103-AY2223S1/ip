package Duke;

/**
 * New task type that extends from task
 */
public class Todo extends Task {

    /**
     * constructor
     *
     * @param name of the task
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * @return string of formatted text in file
     */
    @Override
    public String fileFormat() {
        return "T|" + super.fileFormat();
    }

    /**
     *{@inheritDoc}
     * @return string of desired format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
