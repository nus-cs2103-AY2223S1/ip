package arc;

/**
 * Encapsulates a Note object
 */
public class Note {
    private String title;
    private String description;

    /**
     * Constructor for Note
     * @param title Title of Note
     * @param description Description of Note
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Returns how a Note should be represented
     * @return String representation of Note
     */
    @Override
    public String toString() {
        return String.format("%s <%s />",
                this.title,
                this.description.equals("") ? "empty description" : this.description
        );
    }

    /**
     * Returns how a Note should be stored in a txt file
     * @return String representation of Note
     */
    public String toFileFormat() {
        return String.format("%s|%s", this.title, this.description);
    }
}
