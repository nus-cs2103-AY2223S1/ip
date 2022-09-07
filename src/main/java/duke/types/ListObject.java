package duke.types;

/**
 * Represents a type of list object.
 */
public enum ListObject {
    TASK("task"),
    NOTE("note");

    public final String label;

    private ListObject(String label) {
        this.label = label;
    }
}
