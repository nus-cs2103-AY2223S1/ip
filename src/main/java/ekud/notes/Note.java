package ekud.notes;

/**
 * Info about small snippets of textual information
 */
public class Note {
    private final String description;

    /**
     * Constructor that instantiates a Note object.
     * @param description Description of the note
     */
    public Note(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() { return this.description; }
}
