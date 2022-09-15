package duke.task;

public class Note {
    private final String DESCRIPTION;

    /**
     * Creates a new Note object.
     *
     * @param description The name of the task.
     */
    public Note(String description) {
        super();
        this.DESCRIPTION = description;
    }

    /**
     * Gets the name of the note.
     *
     * @return The String specifying the name of the note.
     */
    public String getDescription() {
        return DESCRIPTION;
    }

    public String toString() {
        return DESCRIPTION;
    }
}
