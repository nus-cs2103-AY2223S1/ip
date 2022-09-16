package duke.task;

/**
 * Represents a tag which can be added to new tasks.
 */
public class Tag {
    private final String TAG_NAME;
    protected static final Tag NO_TAG = new Tag(null);

    /**
     * Constructs a new Tag.
     *
     * @param TAG_NAME Name of the Tag.
     */
    public Tag(String TAG_NAME) {
        this.TAG_NAME = TAG_NAME;
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
        return String.format("#%s", TAG_NAME);
    }
}
