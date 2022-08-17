import org.apache.commons.text.WordUtils;

public class Task {
    private String description;

    /**
     * Initialises a Task with a description.
     *
     * @param description A string representing the Task's description.
     */
    public Task(String description) {
        this.description = WordUtils.wrap(description, 37, "\n   ", false);
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return The string representing the Task.
     */
    @Override
    public String toString() {
        return this.description;
    }
}
