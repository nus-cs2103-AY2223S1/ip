package blob.tasks;

/**
 * The TaskType enum represents the different types of tasks that are supported by the application.
 */
public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private final String abbreviation;

    TaskType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

}
