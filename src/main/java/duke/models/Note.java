package duke.models;

/**
 * Note class for user to add notes to the list of {@code Tasks}
 */
public class Note extends Task {
    /**
     * Initializes a Note object with a description
     *
     * @param description A description detailing the task
     */
    public Note(String description) {
        super(description);
    }

    @Override
    public String getSymbol() {
        return "NOTE";
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String stringToWrite() {
        return this.getSymbol() + "|" + this.getDescription();
    }

    @Override
    public void postponeTask() {

    }

    @Override
    public String toString() {
        return "[NOTE] " + this.getDescription();
    }

}
