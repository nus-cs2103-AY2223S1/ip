package skylark.skylark;

/** Represents an Exception whenever an error occurs in Skylark. */
public class SkylarkException extends Exception {
    /** Description of the Exception that occurs. */
    private final String description;

    /**
     * Represents a SkylarkException object.
     *
     * @param description Description of the Exception that occurred.
     */
    public SkylarkException(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
