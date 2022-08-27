package blob.tasks;

public enum TaskType {
    TODO ("T"),
    EVENT ("E"),
    DEADLINE ("D");

    private final String abbreviation;

    TaskType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

}
