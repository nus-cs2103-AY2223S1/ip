package duke.tasks;

/**
 * Represents to do items on task list and stores their description
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLetterTag() {
        return "T";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdditionalInfo() {
        return " ";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + this.getLetterTag() + "][" + this.getStatusIcon() + "] "
                + this.description + " " + this.getTagString();
    }

}
