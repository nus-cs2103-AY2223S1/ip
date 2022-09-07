package blob.tasks;

/**
 * The Priority enum contains the priorities that can be assigned to tasks.
 */
public enum Priority {
    HIGH("H"),
    MED("M"),
    LOW("L");

    private final String abbreviation;

    Priority(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
