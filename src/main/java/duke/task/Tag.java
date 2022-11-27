package duke.task;

/**
 * Represents a tag which can be added to new tasks.
 */
public class Tag {
    protected static final Tag NO_TAG = new Tag(null);
    private final String tagName;

    /**
     * Constructs a new Tag.
     *
     * @param tagName Name of the Tag.
     */
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns the string representation of this Tag.
     *
     * @return String representing this Tag.
     */
    public String toString() {
        if (this.equals(NO_TAG)) {
            return "";
        }
        return String.format("#%s", tagName);
    }
}
